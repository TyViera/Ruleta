package pe.edu.unp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.unp.domain.User;
import pe.edu.unp.service.UserService;
import pe.edu.unp.util.BackendResponse;

@RestController
@RequestMapping("/user")
public class UserController {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public BackendResponse getUser(@PathVariable String username) {
        BackendResponse response;
        try {
            response = BackendResponse.createFromInfo(userService.findUserByUsername(username));
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            response = BackendResponse.createFromErrorMessage("Ocurrió un error al obtener la información del usuario");
        }
        return response;
    }

    @PostMapping("/create")
    public BackendResponse save(@RequestBody User user) {
        BackendResponse response;
        try {
            userService.save(user);
            response = BackendResponse.createFromSuccessMessage("Usuario creado exitosamente");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            response = BackendResponse.createFromErrorMessage("Ocurrió un error al obtener la información del usuario");
        }
        return response;
    }

    
}
