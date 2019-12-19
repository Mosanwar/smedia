package edu.mum.ea.socialmedia.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import edu.mum.ea.socialmedia.model.Post;
import edu.mum.ea.socialmedia.model.User;
import edu.mum.ea.socialmedia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import edu.mum.ea.socialmedia.model.Advertisment;
import edu.mum.ea.socialmedia.service.AdvertismentService;

@RestController
@CrossOrigin
@RequestMapping("/ads")
public class AdvertismentController {

	public static String uploadDirectory="F:\\Courses\\MUM\\EA\\Solutions\\Social-UI\\src\\assets\\images\\";

	@Autowired
	private AdvertismentService advertismentService;

	@Autowired
	private UserService userService;

	@PostMapping("/add")
	public Advertisment saveAdvertise(@RequestHeader("Authorization") String token, @RequestPart("advertise") Advertisment advertisment,
						 @RequestParam("images") MultipartFile[] files, HttpServletRequest request) {

		MultipartFile photo = files[0];
		if (photo != null && !photo.isEmpty()) {
			try {
				java.util.Date date = new java.util.Date();
				int i = (int) (date.getTime() / 1000);
				String path = uploadDirectory + i + ".png";
				File dirFile = new File(path);
				if (!dirFile.exists()) {
					dirFile.mkdirs();
				}
				photo.transferTo(dirFile);
				advertisment.setImageURL(i + ".png");
			} catch (Exception e) {
				throw new RuntimeException("Product Image saving failed", e);
			}
		}
		//this.template.convertAndSend("/notifications",  post.getUser().getName() + " added a new comment");

		return advertismentService.saveAdvertisment(advertisment);
	}

	@GetMapping("/get")
	public List<Advertisment> getAd() {
		User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		return advertismentService.getAdsByUser(user.getAge(),user.getCity());

	}
	
}
