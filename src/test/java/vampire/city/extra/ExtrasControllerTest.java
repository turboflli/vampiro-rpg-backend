package vampire.city.extra;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExtrasControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void consutlarTodosOsClans() throws Exception {

        // Executa a requisição
        mockMvc.perform(get("/extra/clans")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(31));
    }

    @Test
    void consutlarOsClansPorNome() throws Exception {

        // Executa a requisição
        mockMvc.perform(get("/extra/clans/bruj")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[*].name", containsInAnyOrder("Brujah", "Brujah Verdadeiro")));
    }

    @Test
    void consutlarTodasAsRoads() throws Exception {

        // Executa a requisição
        mockMvc.perform(get("/extra/roads")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(6));
    }

    @Test
    void consutlarAsRoadsPorNome() throws Exception {

        // Executa a requisição
        mockMvc.perform(get("/extra/roads?name=Cae")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Via Caeli"));
    }

    @Test
    void consutlarAsRoadsPorNomeDoPath() throws Exception {

        // Executa a requisição
        mockMvc.perform(get("/extra/roads?pathName=Céu")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].pathName").value("Caminho do Céu"));
    }

}
