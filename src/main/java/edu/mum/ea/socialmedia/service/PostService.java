package edu.mum.ea.socialmedia.service;

import edu.mum.ea.socialmedia.model.Post;
import edu.mum.ea.socialmedia.model.User;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface PostService {
	
	
	//Get a Post
	Post getPost(Long id);
    Page<Post> getAllPosts(String userEmail, int pageNo);
    Page<Post> searchAllPosts(String userEmail, int pageNo,String searchTxt);
    Page<Post> getAllPostsWithFollowers(String userEmail,int pageNo);
    Page<Post> searchAllPostsWithFollowers(String userEmail,int pageNo,String searchTxt);
    void addComment(int postId,String comment);
    void addLike(int postId);
    void removeLike(int postId);

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
   // Page<Product> allProductsSortedByName = productRepository.findAll(Sort.by("name"));
}
