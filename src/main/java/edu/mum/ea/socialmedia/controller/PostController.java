package edu.mum.ea.socialmedia.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.mum.ea.socialmedia.model.Post;
import edu.mum.ea.socialmedia.model.User;
import edu.mum.ea.socialmedia.service.PostService;
import edu.mum.ea.socialmedia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Date;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/post")
public class PostController {

    public static String uploadDirectory="F:\\Courses\\MUM\\EA\\Social-UI\\src\\assets\\images\\";

    @Autowired
	private PostService postService;
    
	@Autowired
    private UserService userService;

    @Autowired
    private SimpMessagingTemplate template;

	@PostMapping("/add")
	public Post savePost(@RequestHeader("Authorization") String token,@RequestPart("post") Post post,
                         @RequestParam("images") MultipartFile[] files, HttpServletRequest request) {
		
		MultipartFile photo = files[0];
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        post.setUser(userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
		if (photo != null && !photo.isEmpty()) {
			try {
				java.util.Date date = new java.util.Date();
				int i = (int) (date.getTime() / 1000);
				String path = uploadDirectory + i + ".png";
                File dirFile = new File(path);
                if (!dirFile.exists()) {
                    dirFile.mkdirs();
                }
				photo.transferTo(dirFile);
				post.setImageURL(i + ".png");
			} catch (Exception e) {
				throw new RuntimeException("Product Image saving failed", e);
			}
		}
        //this.template.convertAndSend("/notifications",  post.getUser().getName() + " added a new comment");

        return postService.savePost(post);
	}
	@GetMapping("getPost")
	public Post getPost(@RequestParam("id") Long id) {
		return postService.getPost(id);
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
    }

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
