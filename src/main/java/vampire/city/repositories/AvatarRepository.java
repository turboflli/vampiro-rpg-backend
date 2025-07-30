package vampire.city.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import vampire.city.model.Avatar;

public interface AvatarRepository extends JpaRepository<Avatar, Integer> {
    
}
