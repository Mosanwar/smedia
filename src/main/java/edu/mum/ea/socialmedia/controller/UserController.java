package edu.mum.ea.socialmedia.controller;

import edu.mum.ea.socialmedia.model.Claim;
import edu.mum.ea.socialmedia.model.User;
import edu.mum.ea.socialmedia.service.ClaimService;
import edu.mum.ea.socialmedia.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Setter
@Getter
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ClaimService claimService;

    @PostMapping("/find")
    public User findByEmail(@RequestBody String email){
        return userService.findByEmail(email);
    }

    @PostMapping("/add")
    public User addUser(@RequestBody User user){
        return userService.add(user);
    }
    @RequestMapping(value = "/activateUser", method = RequestMethod.POST)
    public Boolean activate(Long userId) {
        return userService.activate(userId);
    }


    @PostMapping("/addClaim")
    public ResponseEntity addClaim(@RequestParam("msg") String claimBoudy, @RequestPart("deactivatedUser") User user){
        if((user.getBlocked()==true) && (claimService.getClaimByUserId(user.getId()).isEmpty())){
            Claim c=new Claim(claimBoudy);

            return userService.addClaim(user,c);
        }else{
            return new ResponseEntity("You claimed before or not blocked  ", HttpStatus.FORBIDDEN);
        }

    }
}
