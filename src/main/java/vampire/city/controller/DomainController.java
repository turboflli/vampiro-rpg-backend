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

import vampire.city.service.DomainService;
import vampire.city.model.DomainDTO;
import vampire.city.model.User;
import vampire.city.repositories.DomainRepository;
import vampire.city.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/domains")
public class DomainController {
    @Autowired
    private DomainService domainService;
    @Autowired
    private DomainRepository domainRepository;
    @Autowired
    private UserRepository userRepository;
    
    @ApiOperation(value = "Endpoint Criar Domínio", notes = "Salva um domínio")
    @RequestMapping(value="/save", method= RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<DomainDTO> saveDomain(@ApiParam(name = "domain", example = "{\'name\': \'Nome\', \'color\': \'#fff\'}", value = "Json contendo domínio para ser criado")
                                           @RequestBody DomainDTO domainDTO) throws IllegalAccessException {
        DomainDTO domain = this.domainService.save(domainDTO);
        return ResponseEntity.ok(domain);
    }

    @ApiOperation(value = "Endpoint Atualizar Domínio", notes = "Atualiza um domínio")
    @RequestMapping(value="/update", method= RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<DomainDTO> updateDomain(@ApiParam(name = "domain", example = "{\'id\': 1, \'name\': \'Nome\', \'description\': \'Descrição\'}", value = "Json contendo domínio para ser atualizado")
                                           @RequestBody DomainDTO domainDTO) throws IllegalAccessException {
        if (domainDTO.getId() == null) {
            throw new IllegalArgumentException("Id do domínio não pode ser nulo");
        }
        DomainDTO domain = this.domainService.update(domainDTO);
        return ResponseEntity.ok(domain);
    }

    @ApiOperation(value = "Endpoint Recuperar Domínios", notes = "Recupera todos os domínios do usuário")
    @RequestMapping(value="/all", method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<DomainDTO>> getAllDomains() throws IllegalAccessException {
        User user = this.recoveryUser();
        return ResponseEntity.ok(this.domainService.getAll(user));
    }

    @ApiOperation(value = "Endpoint Recuperar Domínio por nome", notes = "Recupera todos os domínios com o nome contendo o texto digitado")
    @RequestMapping(value="/findByname/{name}", method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<DomainDTO>> getDomainsByName(@ApiParam(name = "name", example = "Nome", value = "Nome do domínio para buscar") @PathVariable(value = "name") String name) throws IllegalAccessException {
        return ResponseEntity.ok(this.domainService.findByName(name));
    }

    @ApiOperation(value = "Endpoint Recuperar Domínio por id", notes = "Recupera o domínio pelo id")
    @RequestMapping(value="/find/{id}", method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<DomainDTO> getDomainsByName(@ApiParam(name = "id", example = "1", value = "id do domínio para buscar") @PathVariable(value = "id") Integer id) throws IllegalAccessException {
        return ResponseEntity.ok(this.domainService.findById(id));
    }

    @ApiOperation(value = "Endpoint Recuperar Domínio por personagem", notes = "Recupera todos os domínios de um personagem")
    @RequestMapping(value="/character/{characterId}", method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<DomainDTO>> getDomainsByCharacterId(@ApiParam(name = "characterId", example = "1", value = "Id do personagem para buscar") @PathVariable(value = "characterId") Integer characterId) throws IllegalAccessException {
        return ResponseEntity.ok(this.domainService.findByCharacterId(characterId));
    }

    @ApiOperation(value = "Endpoint deletar Domínio", notes = "Deleta um domínio já existente.")
    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> delete(@ApiParam(name = "id", example = "2", value = "Id do domínio a ser deletado") @PathVariable(value = "id") Integer id) {
        this.domainRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Endpoint para buscar todos os ids de personagem com dominios")
    @RequestMapping(value="/characterIds", method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Integer>> getCharacterIds() throws IllegalAccessException {
        return ResponseEntity.ok(this.domainService.getCharacterIds());
    }

    private User recoveryUser() throws IllegalAccessException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        return user;
    }
}
