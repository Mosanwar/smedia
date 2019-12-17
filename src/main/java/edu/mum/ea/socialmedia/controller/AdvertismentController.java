package edu.mum.ea.socialmedia.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.mum.ea.socialmedia.model.Advertisment;
import edu.mum.ea.socialmedia.service.AdvertismentService;

@RestController("/ads")
public class AdvertismentController {

	@Autowired
	private AdvertismentService advertismentService;
	
	@PostMapping("/add")
	public Advertisment saveAd(@RequestBody Advertisment advertisment, HttpServletRequest request) {
		
		MultipartFile photo = advertisment.getImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");

		if (photo != null && !photo.isEmpty()) {
			try {
				java.util.Date date = new java.util.Date();
				int i = (int) (date.getTime() / 1000);
				String path = rootDirectory + "resources\\images\\" + i + ".png";
				photo.transferTo(new File(path));
				advertisment.setImageURL("resources\\images\\" + i + ".png");
			} catch (Exception e) {
				throw new RuntimeException("Product Image saving failed", e);
			}
		}
		return advertismentService.saveAdvertisment(advertisment);
	}
	
	@GetMapping("/get")
	public Advertisment getAd(Long id) {
		return advertismentService.getAdvertisment(id);
	}
	
}
