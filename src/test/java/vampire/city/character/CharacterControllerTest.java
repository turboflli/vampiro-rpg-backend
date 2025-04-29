package vampire.city.character;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.SerializationUtils;
import vampire.city.controller.CharacterController;
import vampire.city.model.*;
import vampire.city.repositories.CharacterRepository;
import vampire.city.repositories.UserRepository;
import vampire.city.service.CharacterService;

import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CharacterController.class)
class CharacterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CharacterService characterService;

    @MockBean
    private CharacterRepository characterRepository;

    @MockBean
    private UserRepository userRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void deveCriarPersonagem() throws Exception {
        // Criando um CharacterDTO com dados mínimos
        CharacterDTO dto = new CharacterDTO();
        dto.setName("Draven");
        dto.setClanId(1);
        dto.setRoadId(2);
        dto.setCharisma(3);
        dto.setDexterity(2);
        dto.setBloodpool(10);
        dto.setDisciplines(List.of(new DisciplineDTO("Auspex", 2)));

        // Simulando user na sessão
        User user = new User("teste", "senha", false);
        user.setId(99);

        byte[] userSerialized = SerializationUtils.serialize(user.getId());

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userId", userSerialized);

        // Mock do UserRepository
        Mockito.when(userRepository.findById(99)).thenReturn(java.util.Optional.of(user));

        // Executa a requisição
        mockMvc.perform(post("/characters/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .session(session))
                .andExpect(status().isOk());

        // Verifica se o service foi chamado corretamente
        Mockito.verify(characterService).save(Mockito.eq(dto), Mockito.eq(user));
    }

    @Test
    void deveAtualizarPersonagem() throws Exception {
        // Criando um CharacterDTO com dados mínimos
        CharacterDTO dto = new CharacterDTO();
        dto.setId(1);
        dto.setName("Draven");
        dto.setClanId(1);
        dto.setRoadId(2);
        dto.setCharisma(3);
        dto.setDexterity(2);
        dto.setBloodpool(10);
        dto.setDisciplines(List.of(new DisciplineDTO("Auspex", 2)));

        // Simulando user na sessão
        User user = new User("teste", "senha", false);
        user.setId(99);

        byte[] userSerialized = SerializationUtils.serialize(user.getId());

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userId", userSerialized);

        // Mock do UserRepository
        Mockito.when(userRepository.findById(99)).thenReturn(java.util.Optional.of(user));

        // Executa a requisição
        mockMvc.perform(put("/characters/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .session(session))
                .andExpect(status().isOk());

        // Verifica se o service foi chamado corretamente
        Mockito.verify(characterService).save(Mockito.eq(dto), Mockito.eq(user));
    }

    @Test
    void naoDeveAtualizarPersonagemSemId() throws Exception {
        // Criando um CharacterDTO com dados mínimos
        CharacterDTO dto = new CharacterDTO();
        dto.setName("Draven");
        dto.setClanId(1);
        dto.setRoadId(2);
        dto.setCharisma(3);
        dto.setDexterity(2);
        dto.setBloodpool(10);
        dto.setDisciplines(List.of(new DisciplineDTO("Auspex", 2)));

        // Simulando user na sessão
        User user = new User("teste", "senha", false);
        user.setId(99);

        byte[] userSerialized = SerializationUtils.serialize(user.getId());

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userId", userSerialized);

        // Mock do UserRepository
        Mockito.when(userRepository.findById(99)).thenReturn(java.util.Optional.of(user));

        // Executa a requisição
        mockMvc.perform(put("/characters/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .session(session))
                .andExpect(status().isBadRequest());
    }

    @Test
    void consutlarTodos() throws Exception {

        // Criando um CharacterDTO com dados mínimos
        CharacterDTO dto = new CharacterDTO();
        dto.setName("Draven");
        dto.setClanId(1);
        dto.setRoadId(2);
        dto.setCharisma(3);
        dto.setDexterity(2);
        dto.setBloodpool(10);
        dto.setDisciplines(List.of(new DisciplineDTO("Auspex", 2)));

        // Criando um CharacterDTO com dados mínimos
        CharacterDTO dto2 = new CharacterDTO();
        dto2.setName("Leila");
        dto2.setClanId(2);
        dto2.setRoadId(1);
        dto2.setCharisma(5);
        dto2.setDexterity(3);
        dto2.setBloodpool(10);
        dto2.setDisciplines(List.of(new DisciplineDTO("Auspex", 2)));

        // Simulando user na sessão
        User user = new User("teste", "senha", false);
        user.setId(99);

        byte[] userSerialized = SerializationUtils.serialize(user.getId());

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userId", userSerialized);

        // Mock do UserRepository
        Mockito.when(userRepository.findById(99)).thenReturn(java.util.Optional.of(user));
        Mockito.when(characterService.getAll(user)).thenReturn(List.of(dto, dto2));

        // Executa a requisição
        mockMvc.perform(get("/characters/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].name", containsInAnyOrder("Leila", "Draven")))
                .andExpect(jsonPath("$[*].charisma", containsInAnyOrder(3, 5)));
        // Verifica se o service foi chamado corretamente
        Mockito.verify(characterService).getAll(Mockito.eq(user));
    }

    @Test
    void consutlarPorId() throws Exception {
        // Criando um CharacterDTO com dados mínimos
        CharacterDTO dto = new CharacterDTO();
        dto.setId(1);
        dto.setName("Draven");
        dto.setClanId(1);
        dto.setRoadId(2);
        dto.setCharisma(3);
        dto.setDexterity(2);
        dto.setBloodpool(10);
        dto.setDisciplines(List.of(new DisciplineDTO("Auspex", 2)));
        // Simulando user na sessão
        User user = new User("teste", "senha", false);
        user.setId(99);

        byte[] userSerialized = SerializationUtils.serialize(user.getId());

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userId", userSerialized);

        // Mock do UserRepository
        Mockito.when(userRepository.findById(99)).thenReturn(java.util.Optional.of(user));
        Mockito.when(characterService.findById(1)).thenReturn(dto);

        // Executa a requisição
        mockMvc.perform(get("/characters/find/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Draven"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.charisma").value(3));

        // Verifica se o service foi chamado corretamente
        Mockito.verify(characterService).findById(1);
    }

    @Test
    void deveDeletarPersonagem() throws Exception {

        // Simulando user na sessão
        User user = new User("teste", "senha", false);
        user.setId(99);

        byte[] userSerialized = SerializationUtils.serialize(user.getId());

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userId", userSerialized);

        // Mock do UserRepository
        Mockito.when(userRepository.findById(99)).thenReturn(java.util.Optional.of(user));

        // Executa a requisição
        mockMvc.perform(delete("/characters/delete/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .session(session))
                .andExpect(status().isOk());

        // Verifica se o service foi chamado corretamente
        Mockito.verify(characterRepository).deleteById(2);
    }
}
