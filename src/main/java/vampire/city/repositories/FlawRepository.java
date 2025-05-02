package vampire.city.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import vampire.city.model.Character;
import vampire.city.model.Flaw;

import java.util.List;

public interface FlawRepository extends CrudRepository<Flaw,Integer> {

    List<Flaw> findByCharacter(@Param("character") Character character);
}
