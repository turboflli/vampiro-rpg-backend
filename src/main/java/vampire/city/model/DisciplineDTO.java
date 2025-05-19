package vampire.city.model;

import javax.persistence.Column;
import java.util.Objects;

public class DisciplineDTO {


    private String name;
    private int score;

    public DisciplineDTO(String name, int score) {
        this.name = name;
        this.score = score;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        DisciplineDTO that = (DisciplineDTO) o;
        return score == that.score && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(name);
        result = 31 * result + score;
        return result;
    }
}
