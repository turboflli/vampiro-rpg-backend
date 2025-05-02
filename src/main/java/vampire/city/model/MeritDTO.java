package vampire.city.model;

import java.util.Objects;

public class MeritDTO {

    private String name;
    private int score;
    private TraitType type;

    public MeritDTO(String name, int score, TraitType type) {
        this.name = name;
        this.score = score;
        this.type = type;
    }

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        MeritDTO that = (MeritDTO) o;
        return score == that.score && Objects.equals(name, that.name) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, score, type);
    }
}
