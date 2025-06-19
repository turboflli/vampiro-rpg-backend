package vampire.city.service;

import java.util.List;
import java.util.stream.Collectors;

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
}
