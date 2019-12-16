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

import edu.mum.ea.socialmedia.model.Post;
import edu.mum.ea.socialmedia.service.PostService;

@RestController("/post")
public class PostController {

	@Autowired
	private PostService postService;
	
	@PostMapping("/add")
	public Post savePost(@RequestBody Post post, HttpServletRequest request) {
		
		MultipartFile photo = post.getImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");

		if (photo != null && !photo.isEmpty()) {
			try {
				java.util.Date date = new java.util.Date();
				int i = (int) (date.getTime() / 1000);
				String path = rootDirectory + "resources\\images\\" + i + ".png";
				photo.transferTo(new File(path));
				post.setImageURL("resources\\images\\" + i + ".png");
			} catch (Exception e) {
				throw new RuntimeException("Product Image saving failed", e);
			}
		}
		return postService.savePost(post);
	}
	@GetMapping("getPost")
	public Post getPost(@RequestParam("id") Long id) {
		return postService.getPost(id);
	}
}
