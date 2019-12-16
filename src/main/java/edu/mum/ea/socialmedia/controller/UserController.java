package edu.mum.ea.socialmedia.controller;

import edu.mum.ea.socialmedia.model.User;
import edu.mum.ea.socialmedia.service.UserService;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController("/user")
public class UserController {

    @Autowired
    private UserService userService;

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
}
