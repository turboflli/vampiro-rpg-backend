package vampire.city.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import vampire.city.model.Clan;

import java.util.List;

public interface ClanRepository extends CrudRepository<Clan, Integer> {
    List<Clan> findByNameContainingIgnoreCase(@Param("name") String name);
}
