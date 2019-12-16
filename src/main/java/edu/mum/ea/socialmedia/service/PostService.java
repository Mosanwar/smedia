package edu.mum.ea.socialmedia.service;

import edu.mum.ea.socialmedia.model.Post;

public interface PostService {
	
	//creates a Post
	Post savePost(Post post);
	
	//Get a Post
	Post getPost(Long id);
}
