package vampire.city.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vampire.city.model.Blocks;
import vampire.city.model.User;

public interface BlocksRepository extends JpaRepository<Blocks, Integer> {

    List<Blocks> findByUser(User user);
    
}
