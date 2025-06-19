package vampire.city.model;

import java.util.Objects;

public class RoutineDTO {
    private Integer id;
    private Integer characterId;
    private Integer placeId;
    private int weekday;
    private String startTime;
    private String endTime;
    private String description;

    public RoutineDTO(Integer id, Integer characterId, Integer placeId, int weekday, String startTime, String endTime, String description) {
        this.id = id;
        this.characterId = characterId;
        this.placeId = placeId;
        this.weekday = weekday;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
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

        RoutineDTO routineDTO = (RoutineDTO) o;

        if (!Objects.equals(id, routineDTO.id)) return false;
        if (!Objects.equals(characterId, routineDTO.characterId)) return false;
        if (!Objects.equals(placeId, routineDTO.placeId)) return false;
        if (!Objects.equals(weekday, routineDTO.weekday)) return false;
        if (!Objects.equals(startTime, routineDTO.startTime)) return false;
        if (!Objects.equals(endTime, routineDTO.endTime)) return false;
        return Objects.equals(description, routineDTO.description);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (characterId != null ? characterId.hashCode() : 0);
        result = 31 * result + (placeId != null ? placeId.hashCode() : 0);
        result = 31 * result + weekday;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
