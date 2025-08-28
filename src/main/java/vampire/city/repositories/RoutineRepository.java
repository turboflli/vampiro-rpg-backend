package vampire.city.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vampire.city.model.Character;
import vampire.city.model.Place;
import vampire.city.model.Routine;

public interface RoutineRepository extends JpaRepository<Routine, Integer> {

    List<Routine> findByCharacter(Character character);

    List<Routine> findByPlace(Place place);

    List<Routine> findByWeekday(Integer day);
}
