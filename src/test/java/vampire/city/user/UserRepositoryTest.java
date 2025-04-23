package vampire.city.user;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import vampire.city.initial.BasicServiceTest;
import vampire.city.model.Character;
import vampire.city.model.Clan;
import vampire.city.model.Road;
import vampire.city.repositories.CharacterRepository;
import vampire.city.repositories.ClanRepository;
import vampire.city.repositories.RoadRepository;

import java.util.List;

public class UserRepositoryTest extends BasicServiceTest {

    @Autowired
    private ClanRepository clanRepository;
    @Autowired
    private RoadRepository roadRepository;
    @Autowired
    private CharacterRepository characterRepository;

    @Test
    public void criarVampiro(){
        List<Clan> todos = (List<Clan>) this.clanRepository.findAll();
        System.out.println("Clãs carregados: " + todos.size()); // Debug

        List<Road> todas = (List<Road>) this.roadRepository.findAll();
        System.out.println("Caminhos carregados: " + todas.size()); // Debug


        Clan tzimisce = this.clanRepository.findByNameContainingIgnoreCase("Tzimisce").get(0);
        Road pecado = this.roadRepository.findByPathNameContainingIgnoreCase("Pecado").get(0);
        Character vamp1 = this.createVampire("Char 1",tzimisce, pecado );
        vamp1 = this.characterRepository.save(vamp1);
        Assert.notNull(vamp1.getId());
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
