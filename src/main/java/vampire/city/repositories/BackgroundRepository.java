package vampire.city.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import vampire.city.model.Character;
import vampire.city.model.Background;

import java.util.List;

public interface BackgroundRepository extends CrudRepository<Background, Integer> {

    List<Background> findByCharacter(@Param("character") Character character);
}
