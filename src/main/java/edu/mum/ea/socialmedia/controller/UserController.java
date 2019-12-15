package edu.mum.ea.socialmedia.controller;

import edu.mum.ea.socialmedia.model.AssignRolesData;
import edu.mum.ea.socialmedia.model.User;
import edu.mum.ea.socialmedia.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Setter
@Getter
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/find")
    public User findByEmail(@RequestBody String email){
        return userService.findByEmail(email);
    }

    @PostMapping("/add")
    public User addUser(@RequestBody User user){
        return userService.add(user);
    }

    @PostMapping("assignRoles")
    public User assignRoles(@RequestBody AssignRolesData data){
        return getUserService().assignRoles(data);
    }

    @GetMapping("findAll")
    public List<User> findAll(){
        return getUserService().findAll();
    }
}
