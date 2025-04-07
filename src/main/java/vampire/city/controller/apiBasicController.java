package vampire.city.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import vampire.city.model.User;
import vampire.city.repositories.UserRepository;
import vampire.city.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path="/api")
public class apiBasicController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    /**
     * ENDPOINTS DO USUÁRIO
     * */

    @ApiOperation(value = "Endpoint lista Usuários", notes = "Lista todos os usuários")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<User> listUser() {
        return (List<User>) this.userRepository.findAll();
    }

    @ApiOperation(value = "Endpoint Salvar Usuário", notes = "Salva um novo usuário. É necessário ser um adminitrador para usar essa função")
    @RequestMapping(value="/users", method=RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User saveUser(@ApiParam(name = "user", example = "{'username': 'login', 'senha': 'password', 'nivel': <String>, 'admin': false}", value = "Json contendo usuário para ser criado")
                         @RequestBody  Map<String,Object> json, HttpServletRequest request) throws IllegalAccessException {
        this.blockIfLoggedUserIsntGm(request);
        User savedUser;
        try{
            savedUser = this.userService.save(json);
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        return savedUser;
    }

    @ApiOperation(value = "Endpoint Atualizar Usuário", notes = "Atualiza um usuário já existente. É necessário ser um adminitrador para usar essa função")
    @RequestMapping(value="/users/{id}", method=RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User updateUser(@ApiParam(name = "user", example = "{'username': 'login', 'senha': 'password', 'nivel': <String>, 'admin': false}", value = "Json contendo usuário para ser Atualizado, note que não deve passar o id nesse parametro")
                           @RequestBody  Map<String,Object> json,
                           @ApiParam(name = "id", example = "2", value = "Id do usuário a ser atualizado") @PathVariable(value = "id") Integer userId, HttpServletRequest request) throws Exception{
        this.blockIfLoggedUserIsntGm(request);
        User savedUser;
        try{
            savedUser = this.userService.update(userId, json);
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        return savedUser;
    }

    @ApiOperation(value = "Endpoint deletar Usuário", notes = "Deleta um usuário já existente. É necessário ser um adminitrador para usar essa função")
    @RequestMapping(value="/users/{id}", method=RequestMethod.DELETE)
    @ResponseBody
    public void deleteUser(@ApiParam(name = "id", example = "2", value = "Id do usuário a ser deletado") @PathVariable(value = "id") Integer userId, HttpServletRequest request) throws Exception {
        this.blockIfLoggedUserIsntGm(request);
        try{
            this.userService.delete(userId);
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @ApiOperation(value = "Endpoint Realizar login", notes="Realiza seu login no sistema, para ter acesso as funções")
    @RequestMapping(value="/login", method=RequestMethod.POST,
            produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String login(@ApiParam(name = "credentials", example = "{'username': 'login', 'senha': 'password'}", value = "Json contendo username e senha do usuário") @RequestBody Map<String,Object> json, HttpServletRequest request){
        try{
            User findedUser = this.userService.findByLogin(json);
            byte[] data = SerializationUtils.serialize(findedUser.getId());
            request.getSession().setAttribute("userId", data);

        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        return "logged in";
    }

    @ApiOperation(value = "Endpoint Realizar logout", notes="Realiza seu logout no sistema")
    @RequestMapping(value="/logout", method=RequestMethod.POST,
            produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String logout(HttpServletRequest request){
        try{
            request.getSession().removeAttribute("userId");

        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        return "logged out";
    }

    /**
     * Acess check methods
     * */
    private Integer recoveryUserId(HttpServletRequest request) throws IllegalAccessException {
        byte[] data = (byte[]) request.getSession().getAttribute("userId");
        Integer userId = data != null ? (Integer) SerializationUtils.deserialize(data) : null;
        if (userId == null) {
            throw new IllegalAccessException("Usuário não logado, por favor faça login.");
        }
        return userId;
    }

    private boolean isLoggedUserGm(HttpServletRequest request)  throws IllegalAccessException {
        byte[] data = (byte[]) request.getSession().getAttribute("userId");
        Integer userId = data != null ? (Integer) SerializationUtils.deserialize(data) : null;
        if (userId == null) {
            throw new IllegalAccessException("Usuário não logado, por favor faça login.");
        }
        return this.userRepository.findById(userId).get().isGm();
    }

    private void blockIfLoggedUserIsntGm(HttpServletRequest request) throws IllegalAccessException {
        if (!this.isLoggedUserGm(request)){
            throw new IllegalAccessException("Apenas usuário administrador pode acessar essa função");
        }
    }

}
