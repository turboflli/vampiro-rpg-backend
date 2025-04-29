package vampire.city.character;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import vampire.city.initial.BasicServiceTest;
import vampire.city.model.Character;
import vampire.city.model.Clan;
import vampire.city.model.Discipline;
import vampire.city.model.Road;
import vampire.city.repositories.CharacterRepository;
import vampire.city.repositories.ClanRepository;
import vampire.city.repositories.DisciplineRepository;
import vampire.city.repositories.RoadRepository;

import java.util.ArrayList;
import java.util.Arrays;


public class CharacterRepositoryTest extends BasicServiceTest {

    @Autowired
    private ClanRepository clanRepository;
    @Autowired
    private RoadRepository roadRepository;
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private DisciplineRepository disciplineRepository;

    @Test
    public void criarVampiro(){
        Clan tzimisce = this.clanRepository.findByNameContainingIgnoreCase("Tzimisce").get(0);
        Road pecado = this.roadRepository.findByPathNameContainingIgnoreCase("Pecado").get(0);
        Character vamp1 = this.createVampire("Char 1",tzimisce, pecado );
        vamp1 = this.characterRepository.save(vamp1);
        assertNotNull(vamp1.getId());
    }

    @Test
    public void criarDisciplina(){
        Clan tzimisce = this.clanRepository.findByNameContainingIgnoreCase("Tzimisce").get(0);
        Road pecado = this.roadRepository.findByPathNameContainingIgnoreCase("Pecado").get(0);
        Character vamp1 = this.createVampire("Char 1",tzimisce, pecado );
        Discipline protean = new Discipline("protean",1, vamp1);
        ArrayList<Discipline> disciplinas = new ArrayList<>(Arrays.asList(protean));
        vamp1.setDisciplines(disciplinas);
        vamp1 = this.characterRepository.save(vamp1);
        int numeroDeDisciplinas = this.disciplineRepository.findByCharacter(vamp1).size();
        assertEquals(1, numeroDeDisciplinas);
    }

    @Test
    public void deletarVampiro(){
        Clan brujah = this.clanRepository.findByNameContainingIgnoreCase("Brujah").get(0);
        Road rei = this.roadRepository.findByPathNameContainingIgnoreCase("Rei").get(0);
        Character vamp1 = this.createVampire("Char to Delete",brujah, rei );
        vamp1 = this.characterRepository.save(vamp1);
        assertNotNull(vamp1.getId());
        this.clanRepository.deleteById(vamp1.getId());
        boolean deletado = this.characterRepository.findByUser(this.defaultUser)
                .stream().noneMatch(it -> it.getName().equals("Char to Delete"));
        assertTrue(deletado);
    }

    private Character createVampire(String name,Clan clan, Road road){
        Character vampire = new Character(
        this.defaultUser,
        name,
        clan,
        12,
        "npc",
        "Visionary",
        "Traditionalist",
        "personagem de teste",
        1,
        1,
        1,
        2,
        1,
        2,
        2,
        3,
        2,
        5,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        5,
        4,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        5,
        5,
        3,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        2,
        1,
        0,
        4,
        7,
        3,
        road,
        7,
        7,
        10
        );
        return vampire;
    }
}
