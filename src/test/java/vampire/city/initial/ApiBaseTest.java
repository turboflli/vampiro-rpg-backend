package vampire.city.initial;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import vampire.city.controller.apiBasicController;
import vampire.city.model.User;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.startsWith;
import org.junit.Before;
import org.junit.runner.RunWith;
import vampire.city.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(apiBasicController.class)
@AutoConfigureMockMvc
@Import(apiBasicController.class)
@ComponentScan(basePackages = {"vampire.city"})
@Transactional
public class ApiBaseTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserService userService;
    private User superUser, ownerUser, otherUser;

    @Before
    public void instanciateTestUsers() throws Exception {
        if (superUser == null) {
            Map<String, Object> user = new HashMap<>();
            user.put("username", "superUser");
            user.put("senha", "superUserTestPassword");
            user.put("gm", true);
            superUser = this.userService.save(user);
            user.put("username", "ownerUser");
            user.put("gm", "ownerPassword");
            user.put("admin", false);
            ownerUser = this.userService.save(user);
            user.put("username", "otherUser");
            user.put("senha", "otherPassword");
            otherUser = this.userService.save(user);
        }
    }
}
