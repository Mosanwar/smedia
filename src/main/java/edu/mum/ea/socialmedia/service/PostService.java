package edu.mum.ea.socialmedia.service;

import edu.mum.ea.socialmedia.model.Post;
import edu.mum.ea.socialmedia.model.User;

import java.util.List;

public interface PostService {
	
	//creates a Post
	Post savePost(Post post);
	void detectMeliousPost(Long postId);
	Post getPostById(Long postId);
	Long countByDisabledTruePerUser(Long UserId);
	boolean isAbuser(User user);
	Post deactivatPost(Post post);
	Boolean ignorePost(Long id);
	List<Post> getAllMeltiousPost();
	boolean isBlocked(Long userId);
}
