package vampire.city.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vampire.city.model.Character;
import vampire.city.model.Domain;
import vampire.city.model.User;

public interface DomainRepository extends JpaRepository<Domain, Integer> {

    List<Domain> findByCharacter(Character character);
    @Query("SELECT d FROM Domain d WHERE d.character.user = :user")
    List<Domain> findAllByUser(User user);
    List<Domain> findByNameContainingIgnoreCase(String name);
}
