package vampire.city.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import vampire.city.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("SELECT u FROM users u WHERE u.senha=:senha AND u.username=:username")
    User findByLogin(@Param("username") String username, @Param("senha") String senha);

    @Query("DELETE FROM users u WHERE u.id=:id ")
    @Modifying
    void deleteById(@Param("id") int id);
}
