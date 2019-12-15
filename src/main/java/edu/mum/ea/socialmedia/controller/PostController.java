package edu.mum.ea.socialmedia.controller;

import edu.mum.ea.socialmedia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.mum.ea.socialmedia.model.Post;
import edu.mum.ea.socialmedia.service.PostService;

import java.util.List;

@RestController("/post")
public class PostController {
	@Autowired
    private UserService userService;
	@Autowired
	private PostService postService;
	
	@PostMapping("/add")
	public Post savePost(@RequestBody Post post) {
		if(!postService.isBlocked(post.getUser().getId())){
			return null;
		}

		return postService.savePost(post);
	}

	@RequestMapping(value = "/blockedPost", method = RequestMethod.GET)
	public List<Post> blockedPost() {
		return postService.getAllMeltiousPost();
	}
	@RequestMapping(value = "/ignorePost", method = RequestMethod.POST)
	public Boolean ignorePost(Long postId) {
		return postService.ignorePost(postId);
	}
	@RequestMapping(value = "/deactivatePost", method = RequestMethod.POST)
	public Boolean deactivatePost(Long postId) {
		Post p= postService.getPostById(postId);
		if(postService.deactivatPost(p)!=null){
			return  true;
		}
		return false;
	}}
