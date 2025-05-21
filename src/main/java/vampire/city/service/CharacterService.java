package vampire.city.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vampire.city.mapper.CharacterMapper;
import vampire.city.model.*;
import vampire.city.model.Character;
import vampire.city.repositories.CharacterRepository;
import vampire.city.repositories.ClanRepository;
import vampire.city.repositories.RoadRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class CharacterService {

    @Autowired
    private ClanRepository clanRepository;
    @Autowired
    private RoadRepository roadRepository;
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private CharacterMapper characterMapper;

    public CharacterDTO save(CharacterDTO dto, User user) {
        Character character = fromDTO(dto, user);
        return this.characterMapper.toDTO(characterRepository.save(character));
    }

    public CharacterDTO update(CharacterDTO dto, User user) {
        if(dto.getId() == null){
            throw new IllegalArgumentException("Personagem precisa ter um id para atualizar");
        }
        Character character = fromDTO(dto, user);
        character.setId(dto.getId());
        return this.characterMapper.toDTO(characterRepository.save(character));
    }

    private Character fromDTO(CharacterDTO dto, User user) {
        Clan clan = clanRepository.findById(dto.getClanId())
                .orElseThrow(() -> new RuntimeException("Clan não encontrado"));
        Road road = roadRepository.findById(dto.getRoadId())
                .orElseThrow(() -> new RuntimeException("Road não encontrada"));

        Character character = characterMapper.fromDTO(dto, user, clan, road);
        return character;
    }

    public List<CharacterDTO> getAll(User user) {
        List<Character> characters = this.characterRepository.findByUser(user);
        return characters.stream().map(p -> this.characterMapper.toDTO(p))
                .collect(Collectors.toList());
    }

    public CharacterDTO findById(Integer id) {
        Character character = this.characterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));
        return this.characterMapper.toDTO(character);
    }

    public List<CharacterSummaryDTO> findByName(String name) {
        List<Character> characters = this.characterRepository.findByNameContainingIgnoreCase(name);
        return characters.stream().map(p -> this.characterMapper.toSummaryDTO(p))
                .collect(Collectors.toList());
    }
    
    public List<CharacterSummaryDTO> findSummaryCharacterList(User user){
        List<Character> characters = this.characterRepository.findByUser(user);
        return characters.stream().map(p -> this.characterMapper.toSummaryDTO(p))
                .collect(Collectors.toList());
    }
}
