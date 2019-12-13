package edu.mum.ea.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.ea.socialmedia.model.Post;
import edu.mum.ea.socialmedia.service.PostService;

@RestController("/post")
public class PostController {

	@Autowired
	private PostService postService;
	
	@PostMapping("/add")
	public Post savePost(@RequestBody Post post) {
		return postService.savePost(post);
	}
}
