package vampire.city.character;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.web.servlet.MockMvc;
import vampire.city.controller.CharacterController;
import vampire.city.model.*;
import vampire.city.repositories.CharacterRepository;
import vampire.city.repositories.UserRepository;
import vampire.city.service.CharacterService;
import vampire.city.service.JwtUtil;
import vampire.city.JwtFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import({
    CharacterControllerTest.NoSecurityConfig.class,
    CharacterControllerTest.FakeJwtFilterConfig.class
})
@WebMvcTest(CharacterController.class)
@Disabled
class CharacterControllerTest {

    @TestConfiguration
    static class NoSecurityConfig {
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.csrf().disable().authorizeHttpRequests().anyRequest().permitAll();
            return http.build();
        }
    }

    @TestConfiguration
    static class FakeJwtFilterConfig {
        @Bean
        public JwtFilter jwtFilter() {
            return new JwtFilter() {
                @Override
                protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
                    try {
                        filterChain.doFilter(request, response);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            };
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CharacterService characterService;

    @MockBean
    private CharacterRepository characterRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private JwtUtil jwtUtil;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        when(jwtUtil.extractUsername(anyString())).thenReturn("teste");
        when(jwtUtil.validateToken(anyString(), any())).thenReturn(true);
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

        mockMvc.perform(post("/characters/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .header("Authorization", "Bearer fake-jwt-token"))
                .andExpect(status().isOk());

        Mockito.verify(characterService).save(Mockito.eq(dto), Mockito.any(User.class));
    }

    @Test
    void deveAtualizarPersonagem() throws Exception {
        CharacterDTO dto = new CharacterDTO();
        dto.setId(1);
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
                        .header("Authorization", "Bearer fake-jwt-token"))
                .andExpect(status().isOk());

        Mockito.verify(characterService).update(Mockito.eq(dto), Mockito.any(User.class));
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
                        .header("Authorization", "Bearer fake-jwt-token"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void consutlarTodos() throws Exception {
        CharacterDTO dto1 = new CharacterDTO();
        dto1.setName("Draven");
        dto1.setClanId(1);
        dto1.setRoadId(2);
        dto1.setCharisma(3);
        dto1.setDexterity(2);
        dto1.setBloodpool(10);
        dto1.setDisciplines(List.of(new DisciplineDTO("Auspex", 2)));

        CharacterDTO dto2 = new CharacterDTO();
        dto2.setName("Leila");
        dto2.setClanId(2);
        dto2.setRoadId(1);
        dto2.setCharisma(5);
        dto2.setDexterity(3);
        dto2.setBloodpool(10);
        dto2.setDisciplines(List.of(new DisciplineDTO("Auspex", 2)));

        when(characterService.getAll(any())).thenReturn(List.of(dto1, dto2));

        mockMvc.perform(get("/characters/all")
                        .header("Authorization", "Bearer fake-jwt-token")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].name", containsInAnyOrder("Draven", "Leila")))
                .andExpect(jsonPath("$[*].charisma", containsInAnyOrder(3, 5)));

        Mockito.verify(characterService).getAll(any(User.class));
    }

    @Test
    void consutlarPorId() throws Exception {
        CharacterDTO dto = new CharacterDTO();
        dto.setId(1);
        dto.setName("Draven");
        dto.setClanId(1);
        dto.setRoadId(2);
        dto.setCharisma(3);
        dto.setDexterity(2);
        dto.setBloodpool(10);
        dto.setDisciplines(List.of(new DisciplineDTO("Auspex", 2)));

        when(characterService.findById(1)).thenReturn(dto);

        mockMvc.perform(get("/characters/find/1")
                        .header("Authorization", "Bearer fake-jwt-token")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Draven"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.charisma").value(3));

        Mockito.verify(characterService).findById(1);
    }

    @Test
    void deveDeletarPersonagem() throws Exception {
        mockMvc.perform(delete("/characters/delete/2")
                        .header("Authorization", "Bearer fake-jwt-token")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(characterRepository).deleteById(2);
    }
}
