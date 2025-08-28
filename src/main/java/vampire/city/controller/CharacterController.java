package vampire.city.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import vampire.city.RabbitMQ.CharacterEvents;
import vampire.city.RabbitMQ.NpcProducer;
import vampire.city.model.CharacterDTO;
import vampire.city.model.CharacterSummaryDTO;
import vampire.city.model.User;
import vampire.city.repositories.CharacterRepository;
import vampire.city.repositories.UserRepository;
import vampire.city.service.CharacterService;
import vampire.city.repositories.AvatarRepository;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path="/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private AvatarRepository avatarRepository;

    @Autowired(required = false)
    private NpcProducer producer;

    @ApiOperation(value = "Endpoint Criar Personagem", notes = "Salva um personagem")
    @RequestMapping(value="/save", method= RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<CharacterDTO> saveCharacter(@ApiParam(name = "character", example = "{'name': 'Nome', 'clanId': 15,  'roadId': 12, charisma: 5, disciplines: [{name:'aupex', score:1}]}", value = "Json contendo personagem para ser criado")
                                               @RequestBody CharacterDTO characterdto) throws IllegalAccessException {
        User user = this.recoveryUser();
        CharacterDTO character = this.characterService.save(characterdto, user);
        this.sendNpcEvent(CharacterEvents.CREATE, character.getId());
        return ResponseEntity.ok(character);
    }

    @ApiOperation(value = "Endpoint Atualizar Personagem", notes = "Atualiza um personagem")
    @RequestMapping(value="/update", method= RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<CharacterDTO> updateCharacter(@ApiParam(name = "character", example = "{'id': 1, 'name': 'Nome', 'clanId': 15,  'roadId': 12, charisma: 5, disciplines: [{name:'aupex', score:1}]}", value = "Json contendo personagem para ser atualizado")
                                           @RequestBody CharacterDTO characterdto) throws IllegalAccessException {
        if (characterdto.getId() == null) {
            throw new IllegalArgumentException("Id do personagem não pode ser nulo");
        }
        User user = this.recoveryUser();
        CharacterDTO character = this.characterService.update(characterdto, user);
        this.sendNpcEvent(CharacterEvents.AFTER_UPDATE, character.getId());
        return ResponseEntity.ok(character);
    }

    @ApiOperation(value = "Endpoint Recuperar Personagem", notes = "Recupera todos os personagens do usuário")
    @RequestMapping(value="/all", method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<CharacterDTO>> getAll() throws IllegalAccessException {
        User user = this.recoveryUser();
        return ResponseEntity.ok(this.characterService.getAll(user));
    }

    @ApiOperation(value = "Endpoint Recuperar Resumo dos Personagem", notes = "Recupera apenas nome, clã, caminho e geração dos personagens do usuário")
    @RequestMapping(value="/summary", method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<CharacterSummaryDTO>> getAllSummary() throws IllegalAccessException {
        User user = this.recoveryUser();
        return ResponseEntity.ok(this.characterService.findSummaryCharacterList(user));
    }

    @ApiOperation(value = "Endpoint Recuperar Resumo de um Personagem", notes = "Recupera apenas nome, clã, caminho e geração de um personagem")
    @RequestMapping(value="/summary/{id}", method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<CharacterSummaryDTO> getSummaryById(@ApiParam(name = "id", example = "2", value = "Id do personasgem a ser consultado") @PathVariable(value = "id") Integer id) throws IllegalAccessException {
        return ResponseEntity.ok(this.characterService.findSummaryCharacter(id));
    }

    @ApiOperation(value = "Endpoint Recuperar Personagem por id", notes = "Cunsulta um personagem pelo id")
    @RequestMapping(value="/find/{id}", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<CharacterDTO> getById(@ApiParam(name = "id", example = "2", value = "Id do personasgem a ser consultado") @PathVariable(value = "id") Integer id)  {
        return ResponseEntity.ok(this.characterService.findById(id));

    }

    @ApiOperation(value = "Endpoint Recuperar Personagem por nome", notes = "Cunsulta um personagem pelo nome")
    @RequestMapping(value="/findByName/{name}", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<CharacterSummaryDTO>> getByName(@ApiParam(name = "name", example = "Nome", value = "Nome do personagem a ser consultado") @PathVariable(value = "name") String name)  {
        return ResponseEntity.ok(this.characterService.findByName(name));
    }

    @ApiOperation(value = "Endpoint deletar Personagem", notes = "Deleta um personagem já existente.")
    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> delete(@ApiParam(name = "id", example = "2", value = "Id do personagem a ser deletado") @PathVariable(value = "id") Integer id) {
        this.avatarRepository.deleteById(id);
        this.characterRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private void sendNpcEvent(CharacterEvents event, Integer id) {
        try {
            this.producer.sendNpcEvent(event, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private User recoveryUser() throws IllegalAccessException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        return user;
    }

}
