package vampire.city.mapper;

import org.springframework.stereotype.Component;

import vampire.city.model.DomainDTO;
import vampire.city.model.Domain;
import vampire.city.model.Character;
@Component
public class DomainMapper {
    public DomainDTO toDTO(Domain domain) {
        return new DomainDTO(domain.getId(), domain.getName(), domain.getColor(), domain.getCharacter().getId());
    }
    public Domain fromDTO(DomainDTO dto, Character character) {
        Domain domain = new Domain();
        domain.setId(dto.getId());
        domain.setName(dto.getName());
        domain.setColor(dto.getColor());
        domain.setCharacter(character);
        return domain;
    }
}
