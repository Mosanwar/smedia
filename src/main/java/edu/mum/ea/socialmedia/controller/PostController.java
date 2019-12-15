package edu.mum.ea.socialmedia.controller;

import edu.mum.ea.socialmedia.model.Post;
import edu.mum.ea.socialmedia.model.User;
import edu.mum.ea.socialmedia.service.PostService;
import edu.mum.ea.socialmedia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;


    @PostMapping("/addComment/{postId}/{comment}")
    public void addComment(@PathVariable("postId") int postId,@PathVariable("comment") String comment)
    {
         postService.addComment(postId,comment);
    }

    @PostMapping("/addLike/{postId}")
    public void addLike(@PathVariable("postId") int postId)
    {
        postService.addLike(postId);
    }

    @PostMapping("/removeLike/{postId}")
    public void removeLike(@PathVariable("postId") int postId)
    {
        postService.removeLike(postId);
    }

    @PostMapping("/allPosts/{pageNo}")
    public List<Post> findAllPostsWithFollowers(@RequestBody String email,@PathVariable("pageNo") int pageNo)
    {
        return postService.getAllPostsWithFollowers(email,pageNo).getContent();
    }

    @PostMapping("/searchAllPosts/{pageNo}/{searchTxt}")
    public List<Post> searchAllPostsWithFollowers(@RequestBody String email,@PathVariable("pageNo") int pageNo,@PathVariable("searchTxt") String searchTxt)
    {
        return postService.searchAllPostsWithFollowers(email,pageNo,searchTxt).getContent();
    }


    @PostMapping("/userPosts/{pageNo}")
    public List<Post> findAllPostsWithoutFollowers(@RequestBody String email,@PathVariable("pageNo") int pageNo)
    {
        return postService.getAllPosts(email,pageNo).getContent();
    }

    @PostMapping("/searchUserPosts/{pageNo}/{searchTxt}")
    public List<Post> searchAllPostsWithoutFollowers(@RequestBody String email,@PathVariable("pageNo") int pageNo,@PathVariable("searchTxt") String searchTxt)
    {
        return postService.searchAllPosts(email,pageNo,searchTxt).getContent();
    }



}
