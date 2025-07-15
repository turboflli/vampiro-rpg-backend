package vampire.city.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sub_place")
public class SubPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    public SubPlace() {
        //TODO Auto-generated constructor stub
    }

    public SubPlace(String name, String description, Place place) {
        this.name = name;
        this.description = description;
        this.place = place;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubPlace subPlace = (SubPlace) o;

        if (!Objects.equals(id, subPlace.id)) return false;
        if (!Objects.equals(name, subPlace.name)) return false;
        if (!Objects.equals(description, subPlace.description)) return false;
        return Objects.equals(place, subPlace.place);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (place != null ? place.hashCode() : 0);
        return result;
    }
}
