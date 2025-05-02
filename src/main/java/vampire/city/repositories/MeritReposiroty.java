package vampire.city.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import vampire.city.model.Character;
import vampire.city.model.Merit;

import java.util.List;

public interface MeritReposiroty extends CrudRepository<Merit,Integer> {

    List<Merit> findByCharacter(@Param("character") Character character);
}
