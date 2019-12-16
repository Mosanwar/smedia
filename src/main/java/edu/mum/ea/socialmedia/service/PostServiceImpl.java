package edu.mum.ea.socialmedia.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.ea.socialmedia.model.Post;
import edu.mum.ea.socialmedia.repository.PostRepository;


@Service
@Transactional
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public Post savePost(Post post) {
		// TODO Auto-generated method stub
		return postRepository.save(post);
	}

	@Override
	public Post getPost(Long id) {
		// TODO Auto-generated method stub
		return postRepository.getOne(id);
	}
	

}
