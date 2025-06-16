package vampire.city.character;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vampire.city.model.CharacterDTO;
import vampire.city.model.DisciplineDTO;
import vampire.city.model.LoginRequest;
import vampire.city.repositories.CharacterRepository;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Permite usar @BeforeAll não-estático com injeção
public class CharacterControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CharacterRepository characterRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private String token;

    @BeforeAll
    public void setUp() throws JsonProcessingException, UnsupportedEncodingException, Exception {
        characterRepository.deleteAll();
        String response = mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new LoginRequest("lucas", "lucas"))))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        JSONObject tokenObject = new JSONObject(response);
        this.token = "Bearer " + tokenObject.getString("token");
    }

    @AfterAll
    public void tearDown() {
        characterRepository.deleteAll();
    }

    @Test
    void deveCriarPersonagem() throws Exception {
        CharacterDTO dto = new CharacterDTO();
        dto.setName("Draven");
        dto.setClanId(1);
        dto.setRoadId(2);
        dto.setCharisma(3);
        dto.setDexterity(2);
        dto.setBloodpool(10);
        dto.setDisciplines(List.of(new DisciplineDTO("Auspex", 2)));

        String response = mockMvc.perform(post("/characters/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .header("Authorization", this.token))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        JSONObject responseObject = new JSONObject(response);

        int id = responseObject.getInt("id");
        mockMvc.perform(get("/characters/find/" + id)
                        .header("Authorization", this.token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Draven"))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.charisma").value(3));

        
        mockMvc.perform(delete("/characters/delete/" + id)
                        .header("Authorization", this.token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deveAtualizarPersonagem() throws Exception {

        CharacterDTO dto = new CharacterDTO();
        dto.setName("Draven");
        dto.setClanId(1);
        dto.setRoadId(2);
        dto.setCharisma(3);
        dto.setDexterity(2);
        dto.setBloodpool(10);
        dto.setDisciplines(List.of(new DisciplineDTO("Auspex", 2)));

        String response = mockMvc.perform(post("/characters/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .header("Authorization", this.token))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        JSONObject responseObject = new JSONObject(response);

        int id = responseObject.getInt("id");

        dto.setId(id);
        dto.setCharisma(5);

        mockMvc.perform(put("/characters/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .header("Authorization", this.token))
                .andExpect(status().isOk());

        mockMvc.perform(get("/characters/find/" + id)
                .header("Authorization", this.token)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Draven"))
        .andExpect(jsonPath("$.id").value(id))
        .andExpect(jsonPath("$.charisma").value(5));

        mockMvc.perform(delete("/characters/delete/" + id)
                        .header("Authorization", this.token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void naoDeveAtualizarPersonagemSemId() throws Exception {
        CharacterDTO dto = new CharacterDTO();
        dto.setName("Draven");
        dto.setClanId(1);
        dto.setRoadId(2);
        dto.setCharisma(3);
        dto.setDexterity(2);
        dto.setBloodpool(10);
        dto.setDisciplines(List.of(new DisciplineDTO("Auspex", 2)));

        mockMvc.perform(put("/characters/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .header("Authorization", this.token))
                .andExpect(status().isBadRequest());
    }

    @Test
    void consutlarTodos() throws Exception {
        CharacterDTO dto = new CharacterDTO();
        dto.setName("Draven");
        dto.setClanId(1);
        dto.setRoadId(2);
        dto.setGeneration(12);
        dto.setCharisma(3);
        dto.setDexterity(2);
        dto.setBloodpool(10);
        dto.setDisciplines(List.of(new DisciplineDTO("Auspex", 2)));

        mockMvc.perform(post("/characters/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .header("Authorization", this.token))
                .andExpect(status().isOk());

        dto.setName("Leila");
        dto.setGeneration(7);

        mockMvc.perform(post("/characters/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .header("Authorization", this.token))
                .andExpect(status().isOk());

        String response = mockMvc.perform(get("/characters/summary")
                        .header("Authorization", this.token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

                JSONArray array = new JSONArray(response);
                JSONObject obj = array.getJSONObject(0);
                String name = obj.getString("name");
                assertThat(name, anyOf(is("Draven"), is("Leila")));
                int generation = obj.getInt("generation");
                assertThat(generation, anyOf(is(12), is(7)));
                int id = obj.getInt("id");
                mockMvc.perform(delete("/characters/delete/" + id)
                        .header("Authorization", this.token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

                obj = array.getJSONObject(1);
                id = obj.getInt("id");
                name = obj.getString("name");
                assertThat(name, anyOf(is("Draven"), is("Leila")));
                generation = obj.getInt("generation");
                assertThat(generation, anyOf(is(12), is(7)));
                mockMvc.perform(delete("/characters/delete/" + id)
                        .header("Authorization", this.token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}
