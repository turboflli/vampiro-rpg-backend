package vampire.city.model;

import javax.persistence.*;

import java.time.LocalTime;
import java.util.Objects;

@Table(name = "routine")
@Entity
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "character_id")
    private Character character;
    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;
    private int weekday;
    private LocalTime startTime;
    private LocalTime endTime;
    private String description;

    public Routine(Character character, Place place, int weekday, LocalTime startTime, LocalTime endTime, String description) {
        this.character = character;
        this.place = place;
        this.weekday = weekday;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }

    public Routine() {
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

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Routine routine = (Routine) o;

        if (!Objects.equals(id, routine.id)) return false;
        if (!Objects.equals(character, routine.character)) return false;
        if (!Objects.equals(place, routine.place)) return false;
        if (!Objects.equals(weekday, routine.weekday)) return false;
        if (!Objects.equals(startTime, routine.startTime)) return false;
        if (!Objects.equals(endTime, routine.endTime)) return false;
        return Objects.equals(description, routine.description);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (character != null ? character.hashCode() : 0);
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + weekday;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
