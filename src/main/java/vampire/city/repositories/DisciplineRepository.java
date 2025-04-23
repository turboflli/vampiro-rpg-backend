package vampire.city.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import vampire.city.model.Character;
import vampire.city.model.Discipline;

import java.util.List;

public interface DisciplineRepository extends CrudRepository<Discipline, Integer> {


    List<Discipline> findByCharacter(@Param("character") Character character);
}
