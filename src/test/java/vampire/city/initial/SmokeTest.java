package vampire.city.initial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;

import javax.sql.DataSource;

@SpringBootTest
@ActiveProfiles("test")
public class SmokeTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void testDatabaseConnection() throws Exception {
        try (var conn = dataSource.getConnection()) {
            Assertions.assertFalse(conn.isClosed());
        }
    }
}
