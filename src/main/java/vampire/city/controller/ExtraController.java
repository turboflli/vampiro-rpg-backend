package vampire.city.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vampire.city.model.Clan;
import vampire.city.model.Road;
import vampire.city.repositories.ClanRepository;
import vampire.city.repositories.RoadRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path="/extra")
public class ExtraController {

    @Autowired
    private ClanRepository clanRepository;
    @Autowired
    private RoadRepository roadRepository;

    @ApiOperation(value = "Endpoint Recuperar Clans", notes = "Recupera todos os clans")
    @RequestMapping(value="/clans", method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Clan>> getAllClans(){
        List<Clan> clans = new ArrayList<>();
        this.clanRepository.findAll().forEach(clans::add);
        return ResponseEntity.ok(clans);
    }

    @ApiOperation(value = "Endpoint Recuperar Clans por nome", notes = "Recupera os clans que tenham parte do nome digitado")
    @RequestMapping(value="/clans/{name}", method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Clan>> getClansByName(@ApiParam(name = "name", example = "Tzim", value = "parte do nome do clan para buscar") @PathVariable(value = "name") String name){
        return ResponseEntity.ok(this.clanRepository.findByNameContainingIgnoreCase(name));
    }

    @ApiOperation(value = "Endpoint Recuperar Roads", notes = "Recupera as roads, todas ou as que tenham parte do nome digitado, seja nome ou o nome do path")
    @RequestMapping(value="/roads", method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Road>> getRoadByName(@ApiParam(name = "name", example = "Caeli", value = "parte do nome da road para buscar") @RequestParam(value = "name", required = false) String name,
                                                    @ApiParam(name = "pathName", example = "Céu", value = "parte do nome do path que a road inclui para buscar") @RequestParam(value = "pathName", required = false) String pathName){
        if (name!=null) {
            return ResponseEntity.ok(this.roadRepository.findByNameContainingIgnoreCase(name));
        }
        if (pathName!=null) {
            return ResponseEntity.ok(this.roadRepository.findByPathNameContainingIgnoreCase(pathName));
        }
        List<Road> roads = new ArrayList<>();
        this.roadRepository.findAll().forEach(roads::add);
        return ResponseEntity.ok(roads);
    }

}
