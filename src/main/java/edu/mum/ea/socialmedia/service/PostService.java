package edu.mum.ea.socialmedia.service;

import edu.mum.ea.socialmedia.model.Post;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface PostService {

    Page<Post> getAllPosts(String userEmail, int pageNo);
    Page<Post> searchAllPosts(String userEmail, int pageNo,String searchTxt);
    Page<Post> getAllPostsWithFollowers(String userEmail,int pageNo);
    Page<Post> searchAllPostsWithFollowers(String userEmail,int pageNo,String searchTxt);
    void addComment(int postId,String comment);
    void addLike(int postId);
    void removeLike(int postId);
   // Page<Product> allProductsSortedByName = productRepository.findAll(Sort.by("name"));
}
