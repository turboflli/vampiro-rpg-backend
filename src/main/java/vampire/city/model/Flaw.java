package vampire.city.model;

import javax.persistence.*;

@Entity(name = "flaw")
@Table(name = "flaw")
public class Flaw {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private int score;

    @Enumerated(EnumType.STRING)
    @Column
    private TraitType type;

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

    public TraitType getType() {
        return type;
    }

    public void setType(TraitType type) {
        this.type = type;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Flaw(String name, int score, TraitType type, Character character) {
        this.name = name;
        this.score = score;
        this.type = type;
        this.character = character;
    }

    public Flaw() {
    }
}
