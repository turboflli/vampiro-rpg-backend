package vampire.city.model;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "domain")
@Entity
public class Domain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "character_id")
    private Character character;
    private String color;
    private String name;

    public Domain(Character character, String color, String name) {
        this.character = character;
        this.color = color;
        this.name = name;
    }

    public Domain() {
        //TODO Auto-generated constructor stub
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Domain domain = (Domain) o;

        if (!Objects.equals(id, domain.id)) return false;
        if (!Objects.equals(character, domain.character)) return false;
        if (!Objects.equals(color, domain.color)) return false;
        return Objects.equals(name, domain.name);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (character != null ? character.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

}
