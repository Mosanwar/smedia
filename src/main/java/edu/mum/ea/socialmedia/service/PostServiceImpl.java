package edu.mum.ea.socialmedia.service;

import org.springframework.beans.factory.annotation.Autowired;

import edu.mum.ea.socialmedia.model.Post;
import edu.mum.ea.socialmedia.repository.PostRepository;



public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public Post savePost(Post post) {
		// TODO Auto-generated method stub
		return postRepository.save(post);
	}

}
