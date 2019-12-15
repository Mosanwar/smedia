package edu.mum.ea.socialmedia.service;

import edu.mum.ea.socialmedia.model.BadWord;
import edu.mum.ea.socialmedia.model.User;
import edu.mum.ea.socialmedia.repository.BadWordReposotory;
import org.springframework.beans.factory.annotation.Autowired;

import edu.mum.ea.socialmedia.model.Post;
import edu.mum.ea.socialmedia.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service

public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;
	@Autowired
	BadWordReposotory badWordReposotory;
	@Autowired
	UserService userService;
	
	@Override
	public Post savePost(Post post) {
		// TODO Auto-generated method stub
		return postRepository.save(post);
	}
	@Override
	public void detectMeliousPost(Long postId) {
		Post p=getPostById(postId);
		for (BadWord item : badWordReposotory.findAll()) {
			if(Pattern.compile(Pattern.quote(item.getWord()), Pattern.CASE_INSENSITIVE).matcher(p.getBody()).find()){

				//  if (p.getBody().contains(item.getWord())) {
				p.setMalicious(true);
				break;
			}
		}  postRepository.save(p);

	}
	@Override
	public Post getPostById(Long postId) {
		return postRepository.getPostById(postId).get();
	}

	@Override
	public Long countByDisabledTruePerUser(Long userId) {
		return postRepository.countByDisabledTruePerUser(userId);
	}

	@Override
	public boolean isAbuser(User user) {
		if(countByDisabledTruePerUser(user.getId())>=20)
		{
			return true;

		}else{
			return false;
		}
	}
	@Override
	public boolean isBlocked(Long userId) {
		if(userService.isBlocked(userId))
		{
			return true;

		}else{
			return false;
		}
	}
	@Override
	public Post deactivatPost(Post post) {
		post.setDisabled(true);
		postRepository.save(post);
		return post;
	}
	@Override
	public Boolean ignorePost(Long id) {
		Post p= postRepository.getPostById(id).get();
		p.setMalicious(false);
		postRepository.save(p);
		return true;
	}
	@Override
	public List<Post> getAllMeltiousPost() {
		return postRepository.getAllByMaliciousIsTrue();
	}



}
