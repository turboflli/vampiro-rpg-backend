package vampire.city.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Table(name = "place")
@Entity
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private float x_coordinate;
    private float y_coordinate;
    private byte[] image;
    @ManyToOne
    @JoinColumn(name = "domain_id")
    private Domain domain;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubPlace> subPlaces;

    public Place(String name, String description, float x_coordinate, float y_coordinate, byte[] image, Domain domain, User user, List<SubPlace> subPlaces) {
        this.name = name;
        this.description = description;
        this.x_coordinate = x_coordinate;
        this.y_coordinate = y_coordinate;
        this.image = image;
        this.domain = domain;
        this.user = user;
        this.subPlaces = subPlaces;
    }

    public Place() {
        //TODO Auto-generated constructor stub
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

    public float getX_coordinate() {
        return x_coordinate;
    }

    public void setX_coordinate(float x_coordinate) {
        this.x_coordinate = x_coordinate;
    }

    public float getY_coordinate() {
        return y_coordinate;
    }

    public void setY_coordinate(float y_coordinate) {
        this.y_coordinate = y_coordinate;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<SubPlace> getSubPlaces() {
        return subPlaces;
    }

    public void setSubPlaces(List<SubPlace> subPlaces) {
        this.subPlaces = subPlaces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Place place = (Place) o;

        if (!Objects.equals(id, place.id)) return false;
        if (!Objects.equals(name, place.name)) return false;
        if (!Objects.equals(description, place.description)) return false;
        if (!Objects.equals(x_coordinate, place.x_coordinate)) return false;
        if (!Objects.equals(y_coordinate, place.y_coordinate)) return false;
        if (!Objects.equals(image, place.image)) return false;
        if (!Objects.equals(domain, place.domain)) return false;
        if (!Objects.equals(user, place.user)) return false;
        return Objects.equals(subPlaces, place.subPlaces);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + Float.hashCode(x_coordinate);
        result = 31 * result + Float.hashCode(y_coordinate);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (domain != null ? domain.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (subPlaces != null ? subPlaces.hashCode() : 0);
        return result;
    }
}
