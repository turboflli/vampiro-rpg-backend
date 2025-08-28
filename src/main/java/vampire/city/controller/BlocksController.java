package vampire.city.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import vampire.city.RabbitMQ.PlaceEvents;
import vampire.city.RabbitMQ.PlaceProducer;
import vampire.city.model.BlocksDTO;
import vampire.city.model.User;
import vampire.city.repositories.BlocksRepository;
import vampire.city.repositories.UserRepository;
import vampire.city.service.BlocksService;

@RestController
@RequestMapping("/blocks")
public class BlocksController {
    @Autowired
    private BlocksService blocksService;
    @Autowired
    private UserRepository userRepository;    
    @Autowired
    private BlocksRepository blocksRepository;    
    @Autowired(required = false)
    private PlaceProducer producer;
    
    
    @ApiOperation(value = "Endpoint Criar Bloco", notes = "Salva um bloco")
    @RequestMapping(value="/save", method= RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BlocksDTO> save(@RequestBody BlocksDTO dto) throws IllegalAccessException {
        User user = this.recoveryUser();
        dto = blocksService.save(dto, user);
        this.sendBlockEvent(PlaceEvents.CREATE_BLOCKS, dto.getId());
        return ResponseEntity.ok(dto);
    }

    @ApiOperation(value = "Endpoint Recuperar Blocos", notes = "Recupera todos os blocos do usuário")
    @RequestMapping(value="/all", method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<BlocksDTO>> findByUser() throws IllegalAccessException {
        User user = this.recoveryUser();
        return ResponseEntity.ok(blocksService.findByUser(user));
    }

    @ApiOperation(value = "Endpoint deletar Bloco", notes = "Deleta um bloco já existente.")
    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> delete(@ApiParam(name = "id", example = "2", value = "Id do bloco a ser deletado") @PathVariable(value = "id") Integer id) {
        this.blocksRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private void sendBlockEvent(PlaceEvents event, Integer id) {
        try {
            this.producer.sendBlockEvent(event, id);
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
