package vampire.city.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import vampire.city.model.Character;
import vampire.city.model.User;

import java.util.List;

public interface CharacterRepository  extends CrudRepository<Character, Integer> {

    List<Character> findByUser(@Param("user") User user);
    List<Character> findByNameContainingIgnoreCase(@Param("name") String name);
}
