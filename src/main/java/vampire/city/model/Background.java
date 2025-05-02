package vampire.city.model;

import javax.persistence.*;

@Entity(name="background")
@Table(name="background")
public class Background {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;
    @Column
    private int score;

    @ManyToOne
    @JoinColumn(name = "character_id")
    private Character character;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Background(String name, int score, Character character) {
        this.name = name;
        this.score = score;
        this.character = character;
    }

    public Background() {
    }
}
