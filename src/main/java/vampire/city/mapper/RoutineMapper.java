package vampire.city.mapper;

import java.time.LocalTime;

import vampire.city.model.Place;
import vampire.city.model.Routine;
import vampire.city.model.RoutineDTO;
import vampire.city.model.Character;

public class RoutineMapper {

    public RoutineDTO toDTO(Routine routine) {
        return new RoutineDTO(routine.getId(), routine.getCharacter().getId(), routine.getPlace().getId(), routine.getWeekday(), routine.getStartTime().toString(), routine.getEndTime().toString(), routine.getDescription());
    }
    public Routine fromDTO(RoutineDTO dto, Character character, Place place) {
        LocalTime startTime = LocalTime.parse(dto.getStartTime());
        LocalTime endTime = LocalTime.parse(dto.getEndTime());
        Routine routine = new Routine();
        routine.setId(dto.getId());
        routine.setCharacter(character);
        routine.setPlace(place);
        routine.setWeekday(dto.getWeekday());
        routine.setStartTime(startTime);
        routine.setEndTime(endTime);
        routine.setDescription(dto.getDescription());
        return routine;
    }
}