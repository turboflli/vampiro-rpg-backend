package vampire.city.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vampire.city.model.Domain;
import vampire.city.model.Place;
import vampire.city.model.User;

public interface PlaceRepository extends JpaRepository<Place, Integer> {

    List<Place> findByDomain(Domain domain);

    List<Place> findByNameContainingIgnoreCase(String name);

    List<Place> findByUser(User user);
}
