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

import java.util.List;

import vampire.city.service.RoutineService;
import vampire.city.model.RoutineDTO;
import vampire.city.repositories.RoutineRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/routines")
public class RoutineController {
    @Autowired
    private RoutineService routineService;
    @Autowired
    private RoutineRepository routineRepository;
    
    @ApiOperation(value = "Endpoint Criar Rotina", notes = "Salva uma rotina")
    @RequestMapping(value="/save", method= RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<RoutineDTO> saveRoutine(@ApiParam(name = "routine", example = "{\'name\': \'Nome\', \'description\': \'Descrição\', \'domainId\': 1}", value = "Json contendo rotina para ser criada")
                                           @RequestBody RoutineDTO routineDTO) throws IllegalAccessException {
        RoutineDTO routine = this.routineService.save(routineDTO);
        return ResponseEntity.ok(routine);
    }

    @ApiOperation(value = "Endpoint Atualizar Rotina", notes = "Atualiza uma rotina")
    @RequestMapping(value="/update", method= RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<RoutineDTO> updateRoutine(@ApiParam(name = "routine", example = "{\'id\': 1, \'name\': \'Nome\', \'description\': \'Descrição\', \'domainId\': 1}", value = "Json contendo rotina para ser atualizada")
                                           @RequestBody RoutineDTO routineDTO) throws IllegalAccessException {
        if (routineDTO.getId() == null) {
            throw new IllegalArgumentException("Id da rotina não pode ser nulo");
        }
        RoutineDTO routine = this.routineService.update(routineDTO);
        return ResponseEntity.ok(routine);
    }

    @ApiOperation(value = "Endpoint Recuperar Rotinas por personagem", notes = "Recupera todas as rotinas de um personagem")
    @RequestMapping(value="/character/{characterId}", method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<RoutineDTO>> getRoutinesByCharacter(@ApiParam(name = "characterId", example = "1", value = "Id do personagem para buscar") @PathVariable(value = "characterId") Integer characterId) throws IllegalAccessException {
        return ResponseEntity.ok(this.routineService.findByCharacter(characterId));
    }

    @ApiOperation(value = "Endpoint Recuperar Rotinas por lugar", notes = "Recupera todas as rotinas de um lugar")
    @RequestMapping(value="/place/{placeId}", method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<RoutineDTO>> getRoutinesByPlace(@ApiParam(name = "placeId", example = "1", value = "Id do lugar para buscar") @PathVariable(value = "placeId") Integer placeId) throws IllegalAccessException {
        return ResponseEntity.ok(this.routineService.findByPlace(placeId));
    }

    @ApiOperation(value = "Endpoint deletar Rotina", notes = "Deleta uma rotina já existente.")
    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> delete(@ApiParam(name = "id", example = "2", value = "Id da rotina a ser deletada") @PathVariable(value = "id") Integer id) {
        this.routineRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
