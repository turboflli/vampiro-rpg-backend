package vampire.city.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import vampire.city.model.Road;

import java.util.List;

public interface RoadRepository extends CrudRepository<Road, Integer> {
    List<Road> findByNameContainingIgnoreCase(@Param("name") String name);

    List<Road> findByPathNameContainingIgnoreCase(@Param("pathName") String pathName);
}
