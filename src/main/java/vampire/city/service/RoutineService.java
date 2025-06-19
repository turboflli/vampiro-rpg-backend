package vampire.city.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vampire.city.mapper.RoutineMapper;
import vampire.city.model.Routine;
import vampire.city.model.RoutineDTO;
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

    public RoutineDTO save(RoutineDTO dto) {
        Character character = characterRepository.findById(dto.getCharacterId())
                .orElseThrow(() -> new IllegalArgumentException("Personagem nao encontrado"));
        Place place = placeRepository.findById(dto.getPlaceId())
                .orElseThrow(() -> new IllegalArgumentException("Lugar nao encontrado"));
        Routine routine = routineMapper.fromDTO(dto, character, place);
        return routineMapper.toDTO(routineRepository.save(routine));
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
        return routineMapper.toDTO(routineRepository.save(routine));
    }

    public List<RoutineDTO> findByCharacter(Integer characterId){
        Character character = characterRepository.findById(characterId)
                .orElseThrow(() -> new IllegalArgumentException("Personagem nao encontrado"));
        List<Routine> routines = this.routineRepository.findByCharacter(character);
        return routines.stream().map(r -> this.routineMapper.toDTO(r))
                .collect(Collectors.toList());
    }
    
    public List<RoutineDTO> findByPlace(Integer placeId){
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new IllegalArgumentException("Lugar nao encontrado"));
        List<Routine> routines = this.routineRepository.findByPlace(place);
        return routines.stream().map(r -> this.routineMapper.toDTO(r))
                .collect(Collectors.toList());
    }
}