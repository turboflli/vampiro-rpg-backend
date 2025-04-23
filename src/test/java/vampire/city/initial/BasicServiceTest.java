package vampire.city.initial;

import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ActiveProfiles;


import org.springframework.boot.test.context.SpringBootTest;
import vampire.city.model.User;
import vampire.city.repositories.UserRepository;


@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Permite usar @BeforeAll não-estático com injeção
public class BasicServiceTest {

    @Autowired
    private UserRepository userRepository;

    public static User defaultUser;

    @BeforeAll
    public void setUp() {
        if (defaultUser == null) {
            User newUser = new User("padrão", "teste", true);
            defaultUser = userRepository.save(newUser);
        }
    }
}