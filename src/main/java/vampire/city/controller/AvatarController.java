package vampire.city.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import vampire.city.model.Avatar;
import vampire.city.repositories.AvatarRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/avatars")
public class AvatarController {
    
    @Autowired
    private AvatarRepository avatarRepository;

    @ApiOperation(value = "Endpoint Criar Avatar", notes = "Salva um avatar")
    @RequestMapping(value="/save", method= RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Avatar> saveAvatar(@ApiParam(name = "avatar", example = "{'id': 1, 'hairColor': '000000', 'skinColor': 'FFFFFF', }", value = "Json contendo avatar para ser criado") @RequestBody Avatar avatar) {
        if (avatar.getId() == null) {
            throw new IllegalArgumentException("Id do avatar não pode ser nulo");
        }   
        avatarRepository.save(avatar);
        return ResponseEntity.ok(avatar);
    }

    @ApiOperation(value = "Endpoint Atualizar Avatar", notes = "Atualiza um avatar")
    @RequestMapping(value="/update", method= RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Avatar> updateAvatar(@ApiParam(name = "avatar", example = "{'id': 1, 'hairColor': '000000', 'skinColor': 'FFFFFF', }", value = "Json contendo avatar para ser atualizado") @RequestBody Avatar avatar) {
        if (avatar.getId() == null) {
            throw new IllegalArgumentException("Id do avatar não pode ser nulo");
        }
        avatarRepository.save(avatar);
        return ResponseEntity.ok(avatar);
    }

    @ApiOperation(value = "Endpoint Recuperar Avatar", notes = "Recupera um avatar")
    @RequestMapping(value="/find/{id}", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Avatar> getById(@ApiParam(name = "id", example = "2", value = "Id do avatar a ser consultado") @PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(this.avatarRepository.findById(id).get());
    }
}
