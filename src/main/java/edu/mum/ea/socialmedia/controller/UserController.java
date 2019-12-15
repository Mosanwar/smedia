package edu.mum.ea.socialmedia.controller;

import edu.mum.ea.socialmedia.model.User;
import edu.mum.ea.socialmedia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
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

    @PostMapping("/GetFollowings")
    public List<User> findFollowings(@RequestBody String email)
    {
        return userService.findFollowings(email);
    }
    @PostMapping("/DeleteFollowing/{userEmail}/{followingEmail}")
    public void deleteFollowings(@PathVariable("userEmail") String userEmail, @PathVariable("followingEmail") String followingEmail)
    {
         userService.deleteFollowing(userEmail,followingEmail);
    }
    @PostMapping("/AddFollowing/{userEmail}/{followingEmail}")
    public User addFollowings(@PathVariable("userEmail") String userEmail, @PathVariable("followingEmail") String followingEmail)
    {
        userService.addFollowing(userEmail,followingEmail);
        return userService.findByEmail(followingEmail);
    }
}
