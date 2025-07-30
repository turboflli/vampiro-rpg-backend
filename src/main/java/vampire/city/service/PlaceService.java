package vampire.city.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vampire.city.mapper.PlaceMapper;
import vampire.city.model.Domain;
import vampire.city.model.Place;
import vampire.city.model.PlaceDTO;
import vampire.city.repositories.DomainRepository;
import vampire.city.repositories.PlaceRepository;
import vampire.city.model.User;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private PlaceMapper placeMapper;
    @Autowired
    private DomainRepository domainRepository;
    @Autowired
    private DataSource dataSource;
    
    public PlaceDTO save(PlaceDTO dto, User user) {
        Domain domain = null;
        if(dto.getDomainId() != null){
            domain = domainRepository.findById(dto.getDomainId())
                    .orElseThrow(() -> new IllegalArgumentException("Dominio nao encontrado"));
        }
        Place place = placeMapper.fromDTO(dto, domain);
        place.setUser(user);
        return this.placeMapper.toDTO(placeRepository.save(place));
    }

    public PlaceDTO update(PlaceDTO dto, User user) {
        if(dto.getId() == null){
            throw new IllegalArgumentException("Lugar precisa ter um id para atualizar");
        }
        Domain domain = null;
        if(dto.getDomainId() != null){
            domain = domainRepository.findById(dto.getDomainId())
                    .orElseThrow(() -> new IllegalArgumentException("Dominio nao encontrado"));
        }
        Place place = placeMapper.fromDTO(dto, domain);
        place.setId(dto.getId());
        place.setUser(user);
        return this.placeMapper.toDTO(placeRepository.save(place));
    }

    public List<PlaceDTO> findByDomainId(Integer domainId) {
        Domain domain = domainRepository.findById(domainId)
                .orElseThrow(() -> new IllegalArgumentException("Dominio nao encontrado"));
        List<Place> places = this.placeRepository.findByDomain(domain);
        return places.stream().map(p -> this.placeMapper.toDTO(p))
                .collect(Collectors.toList());
    }

    public List<PlaceDTO> findByUser(User user) {
        List<Place> places = this.placeRepository.findByUser(user);
        return places.stream().map(p -> this.placeMapper.toDTO(p))
                .collect(Collectors.toList());
    }

    public List<PlaceDTO> findByName(String name) {
        List<Place> places = this.placeRepository.findByNameContainingIgnoreCase(name);
        return places.stream().map(p -> this.placeMapper.toDTO(p))
                .collect(Collectors.toList());
    }

    public PlaceDTO findById(Integer id) {
        Place place = this.placeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Lugar nao encontrado"));
        return this.placeMapper.toDTO(place);
    }

    public void removeBlocksColor(float x_coordinate, float y_coordinate) {
        String sql = "UPDATE blocks SET color = '#777777' WHERE ST_Contains(shape, ST_SetSRID(ST_MakePoint(?, ?), 4326))";
    try (
        Connection conn = dataSource.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)
    ) {
        pstmt.setFloat(1, x_coordinate);
        pstmt.setFloat(2, y_coordinate);
        int rows = pstmt.executeUpdate();
        System.out.println("Blocos atualizados: " + rows);
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao atualizar cor dos blocos", e);
    }
    }

    public void fillBlocksColor(PlaceDTO placeDTO) {
        if (placeDTO.getDomainId() != null){
            Domain domain = this.domainRepository.findById(placeDTO.getDomainId())
                .orElseThrow(() -> new IllegalArgumentException("Dominio nao encontrado"));
            String color = domain.getColor();
            String sql = "UPDATE blocks SET color = ? WHERE ST_Contains(shape, ST_SetSRID(ST_MakePoint(?, ?), 4326))";
            try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
            ) {
                pstmt.setString(1, color);
                pstmt.setFloat(2, placeDTO.getX_coordinate());
                pstmt.setFloat(3, placeDTO.getY_coordinate());
                int rows = pstmt.executeUpdate();
                System.out.println("Blocos atualizados: " + rows);
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao atualizar cor dos blocos", e);
            }
        }
    }
}
