package vampire.city.model;

import org.locationtech.jts.geom.Polygon;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;
import vampire.city.model.User;

@Entity(name = "blocks")
@Table(name = "blocks")
public class Blocks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // O tipo Polygon do JTS + anotação do Hibernate
    @Column(columnDefinition = "geometry(POLYGON,4326)", nullable = false)
    private Polygon shape;

    private String color;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Blocks() {}

    public Blocks(Polygon shape, String color, User user) {
        this.shape = shape;
        this.color = color;
        this.user = user;
    }

    // Getters e setters...
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Polygon getShape() {
        return shape;
    }
    
    public void setShape(Polygon shape) {
        this.shape = shape;
    }
    
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Blocks blocks = (Blocks) o;

        if (!Objects.equals(id, blocks.id)) return false;
        if (!Objects.equals(shape, blocks.shape)) return false;
        if (!Objects.equals(color, blocks.color)) return false;
        return Objects.equals(user, blocks.user);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (shape != null ? shape.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
