package vampire.city.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import vampire.city.mapper.DomainMapper;
import vampire.city.model.Domain;
import vampire.city.model.DomainDTO;
import vampire.city.model.Character;
import vampire.city.model.User;
import vampire.city.repositories.CharacterRepository;
import vampire.city.repositories.DomainRepository;

@Service
public class DomainService {
    @Autowired
    private DomainRepository domainRepository;
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private DomainMapper domainMapper;

    public DomainDTO save(DomainDTO dto) {
        Domain domain = fromDTO(dto);
        return this.domainMapper.toDTO(domainRepository.save(domain));
    }

    public DomainDTO update(DomainDTO dto) {
        if(dto.getId() == null){
            throw new IllegalArgumentException("Dominio precisa ter um id para atualizar");
        }
        Domain domain = fromDTO(dto);
        domain.setId(dto.getId());
        return this.domainMapper.toDTO(domainRepository.save(domain));
    }

    private Domain fromDTO(DomainDTO dto) {
        Domain domain = new Domain();
        domain.setId(dto.getId());
        domain.setName(dto.getName());
        domain.setColor(dto.getColor());
        domain.setCharacter(characterRepository.findById(dto.getCharacterId()).orElseThrow(() -> new IllegalArgumentException("Personagem nao encontrado")));
        return domain;
    }

    public List<DomainDTO> getAll(User user) {
        List<Domain> domains = this.domainRepository.findAllByUser(user);
        return domains.stream().map(p -> this.domainMapper.toDTO(p))
                .collect(Collectors.toList());
    }

    public List<DomainDTO> findByCharacterId(Integer characterId) {
        Character character = characterRepository.findById(characterId)
                .orElseThrow(() -> new IllegalArgumentException("Personagem nao encontrado"));
        List<Domain> domains = this.domainRepository.findByCharacter(character);
        return domains.stream().map(p -> this.domainMapper.toDTO(p))
                .collect(Collectors.toList());
    }

    public List<DomainDTO> findByName(String name) {
        List<Domain> domains = this.domainRepository.findByNameContainingIgnoreCase(name);
        return domains.stream().map(p -> this.domainMapper.toDTO(p))
                .collect(Collectors.toList());
    }

    public DomainDTO findById(Integer id) {
        Domain domain = this.domainRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Dominio nao encontrado"));
        return this.domainMapper.toDTO(domain);
    }

    public List<Integer> getCharacterIds() {
        return this.domainRepository.findAll().stream()
                .map(p -> p.getCharacter().getId())
                .collect(Collectors.toList());
    }
}
