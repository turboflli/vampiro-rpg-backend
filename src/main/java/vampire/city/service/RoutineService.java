package vampire.city.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vampire.city.mapper.RoutineMapper;
import vampire.city.model.Routine;
import vampire.city.model.RoutineDTO;
import vampire.city.model.RoutineExibitionDTO;
import vampire.city.repositories.RoutineRepository;
import vampire.city.repositories.CharacterRepository;
import vampire.city.repositories.PlaceRepository;
import vampire.city.model.Character;
import vampire.city.model.Place;

@Service
public class RoutineService {
    @Autowired
    private RoutineRepository routineRepository;
    @Autowired
    private RoutineMapper routineMapper;
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private DataSource dataSource;

    public RoutineDTO save(RoutineDTO dto) {
        Character character = characterRepository.findById(dto.getCharacterId())
                .orElseThrow(() -> new IllegalArgumentException("Personagem nao encontrado"));
        Place place = placeRepository.findById(dto.getPlaceId())
                .orElseThrow(() -> new IllegalArgumentException("Lugar nao encontrado"));
        Routine routine = routineMapper.fromDTO(dto, character, place);
        routine = routineRepository.save(routine);
        return routineMapper.toDTO(routine);
    }

    public RoutineDTO update(RoutineDTO dto) {
        if(dto.getId() == null){
            throw new IllegalArgumentException("Rotina precisa ter um id para atualizar");
        }
        Character character = characterRepository.findById(dto.getCharacterId())
                .orElseThrow(() -> new IllegalArgumentException("Personagem nao encontrado"));
        Place place = placeRepository.findById(dto.getPlaceId())
                .orElseThrow(() -> new IllegalArgumentException("Lugar nao encontrado"));
        Routine routine = routineMapper.fromDTO(dto, character, place);
        routine.setId(dto.getId());
        routine = routineRepository.save(routine);
        return routineMapper.toDTO(routine);
    }

    public List<RoutineExibitionDTO> findByCharacter(Integer characterId){
        Character character = characterRepository.findById(characterId)
                .orElseThrow(() -> new IllegalArgumentException("Personagem nao encontrado"));
        List<Routine> routines = this.routineRepository.findByCharacter(character);
        return routines.stream().map(r -> this.routineMapper.toExibitionDTO(r))
                .collect(Collectors.toList());
    }
    
    public List<RoutineExibitionDTO> findByPlace(Integer placeId){
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new IllegalArgumentException("Lugar nao encontrado"));
        List<Routine> routines = this.routineRepository.findByPlace(place);
        return routines.stream().map(r -> this.routineMapper.toExibitionDTO(r))
                .collect(Collectors.toList());
    }

    public RoutineDTO findById(Integer id) {
        Routine routine = this.routineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rotina nao encontrada"));
        return this.routineMapper.toDTO(routine);
    }

    public List<RoutineExibitionDTO> findByWeekday(Integer day) {

        String sql = "SELECT r.* ,c.name as characterName, p.name as placeName\n" + //
                                "FROM routine r inner join character c on c.id = r.character_id inner join place p on p.id=r.place_id\n" + //
                                "WHERE \n" + //
                                "    (weekday = ? ) \n" + //
                                "    OR \n" + //
                                "    (\n" + //
                                "        weekday = CASE \n" + //
                                "                     WHEN ? = 1 THEN 7 \n" + //
                                "                     ELSE ? - 1 \n" + //
                                "                  END\n" + //
                                "        AND start_time BETWEEN '18:00:00' AND '23:59:59' and end_time BETWEEN '00:00:00' AND '06:00:00' \n" + //
                                "    ) ";
        try (
            Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, day);
            pstmt.setInt(2, day);
            pstmt.setInt(3, day);
            pstmt.executeQuery();
            List<RoutineExibitionDTO> routines = new ArrayList<>();
            ResultSet result = pstmt.getResultSet();
            
            while (result.next()) {
                routines.add(new RoutineExibitionDTO(
                    result.getInt("id"),
                    result.getInt("character_id"),
                    result.getInt("place_id"),
                    result.getInt("weekday"),
                    result.getString("start_time"),
                    result.getString("end_time"),
                    result.getString("description"),
                    result.getString("characterName"),
                    result.getString("placeName")
                ));
            }
            result.close();
            pstmt.close();
            conn.close();
            return routines;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar se existe rotina para o personagem", e);
        }
    }
}