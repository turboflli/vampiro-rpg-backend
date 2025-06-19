package vampire.city.model;

import java.util.Objects;

public class DomainDTO {
    private Integer id;
    private String name;
    private String color;
    private Integer characterId;

    public DomainDTO(Integer id, String name, String color, Integer characterId) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.characterId = characterId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DomainDTO domainDTO = (DomainDTO) o;

        if (!Objects.equals(id, domainDTO.id)) return false;
        if (!Objects.equals(name, domainDTO.name)) return false;
        if (!Objects.equals(color, domainDTO.color)) return false;
        return Objects.equals(characterId, domainDTO.characterId);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (characterId != null ? characterId.hashCode() : 0);
        return result;
    }
}
