package edu.mum.ea.socialmedia.controller;

import edu.mum.ea.socialmedia.model.AssignRolesData;
import edu.mum.ea.socialmedia.model.Claim;
import edu.mum.ea.socialmedia.model.User;
import edu.mum.ea.socialmedia.service.ClaimService;
import edu.mum.ea.socialmedia.service.UserService;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    private ClaimService claimService;

    @PostMapping("/find")
    public User findByEmail(@RequestBody String email){
        return userService.findByEmail(email);
    }

    @PostMapping("/add")
    public User addUser(@RequestBody User user, HttpServletRequest request){
        
    	MultipartFile photo = user.getImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");

		if (photo != null && !photo.isEmpty()) {
			try {
				java.util.Date date = new java.util.Date();
				int i = (int) (date.getTime() / 1000);
				String path = rootDirectory + "resources\\images\\" + i + ".png";
				photo.transferTo(new File(path));
				user.setImageURL("resources\\images\\" + i + ".png");
			} catch (Exception e) {
				throw new RuntimeException("Product Image saving failed", e);
			}
		}
    	
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
    @RequestMapping(value = "/activateUser", method = RequestMethod.POST)
    public Boolean activate(Long userId) {
        return userService.activate(userId);
    }


    @PostMapping("/addClaim/{msg}")
    public void addClaim(@PathVariable("msg") String claimBoudy, @RequestBody User user){
        user = getUserService().findUserById(user.getId());
        //if((user.getBlocked()==true) && (claimService.getClaimByUserId(user.getId()).isEmpty())){
            Claim c=new Claim(claimBoudy);
            userService.addClaim(user,c);
        //}else{
            //return new ResponseEntity("You claimed before or not blocked  ", HttpStatus.FORBIDDEN);
       // }

    }

    @PostMapping("/GetFollowings")
    public List<User> findFollowings(@RequestBody String email)
    {
        SecurityContextHolder.getContext().getAuthentication().getName();
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
