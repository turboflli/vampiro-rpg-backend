package vampire.city.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

import vampire.city.service.PlaceService;
import vampire.city.model.PlaceDTO;
import vampire.city.model.User;
import vampire.city.repositories.PlaceRepository;
import vampire.city.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/places")
public class PlaceController {
    @Autowired
    private PlaceService placeService;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private UserRepository userRepository;
    
    
    @ApiOperation(value = "Endpoint Criar Lugar", notes = "Salva um lugar")
    @RequestMapping(value="/save", method= RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PlaceDTO> savePlace(@ApiParam(name = "place", example = "{'name': 'Nome', 'description': 'Descrição', 'x_coordinate': 1, 'y_coordinate': 1, 'image': 'Imagem', 'domainId': 1}", value = "Json contendo lugar para ser criado")
                                           @RequestBody PlaceDTO placeDTO) throws IllegalAccessException {
        User user = this.recoveryUser();
        PlaceDTO place = this.placeService.save(placeDTO, user);
        return ResponseEntity.ok(place);
    }

    @ApiOperation(value = "Endpoint Atualizar Lugar", notes = "Atualiza um lugar")
    @RequestMapping(value="/update", method= RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PlaceDTO> updatePlace(@ApiParam(name = "place", example = "{'id': 1, 'name': 'Nome', 'description': 'Descrição', 'x_coordinate': 1, 'y_coordinate': 1, 'image': 'Imagem', 'domainId': 1}", value = "Json contendo lugar para ser atualizado")
                                           @RequestBody PlaceDTO placeDTO) throws IllegalAccessException {
        if (placeDTO.getId() == null) {
            throw new IllegalArgumentException("Id do lugar não pode ser nulo");
        }
        User user = this.recoveryUser();
        PlaceDTO place = this.placeService.update(placeDTO, user);
        return ResponseEntity.ok(place);
    }

    @ApiOperation(value = "Endpoint Recuperar Lugar", notes = "Recupera todos os lugares do usuário")
    @RequestMapping(value="/all", method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<PlaceDTO>> getAllPlaces() throws IllegalAccessException {
        User user = this.recoveryUser();
        return ResponseEntity.ok(this.placeService.findByUser(user));
    }

    @ApiOperation(value = "Endpoint Recuperar Lugar por domainId", notes = "Recupera todos os lugares do dominio")
    @RequestMapping(value="/domain/{domainId}", method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<PlaceDTO>> getPlacesByDomainId(@ApiParam(name = "domainId", example = "1", value = "Id do dominio") @PathVariable(value = "domainId") Integer domainId) throws IllegalAccessException {
        return ResponseEntity.ok(this.placeService.findByDomainId(domainId));
    }

    @ApiOperation(value = "Endpoint Recuperar Lugar por nome", notes = "Recupera todos os lugares com o nome contendo o texto digitado")
    @RequestMapping(value="/name/{name}", method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<PlaceDTO>> getPlacesByName(@ApiParam(name = "name", example = "Nome", value = "Nome do lugar para buscar") @PathVariable(value = "name") String name) throws IllegalAccessException {
        return ResponseEntity.ok(this.placeService.findByName(name));
    }

    @ApiOperation(value = "Endpoint deletar Lugar", notes = "Deleta um lugar já existente.")
    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> delete(@ApiParam(name = "id", example = "2", value = "Id do lugar a ser deletado") @PathVariable(value = "id") Integer id) {
        this.placeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private User recoveryUser() throws IllegalAccessException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        return user;
    }
}
