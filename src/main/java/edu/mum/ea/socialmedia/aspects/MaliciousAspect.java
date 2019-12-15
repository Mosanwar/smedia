package edu.mum.ea.socialmedia.aspects;

import edu.mum.ea.socialmedia.model.Post;
import edu.mum.ea.socialmedia.model.User;
import edu.mum.ea.socialmedia.service.PostService;
import edu.mum.ea.socialmedia.service.PostServiceImpl;
import edu.mum.ea.socialmedia.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Aspect
@Configuration
public class MaliciousAspect {
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;
    @AfterReturning(value="execution(* edu.mum.ea.socialmedia.service.PostServiceImpl.savePost(..))" ,returning = "ret")
    public void afterSavingPost(JoinPoint joinPoint, Optional<Post>ret){
        if (ret.get()!=null){
            Post p= ret.get();
            postService.detectMeliousPost(p.getId());
            System.out.println("p:"+p.getMalicious());
        }
    }
    @AfterReturning(value = "execution(* edu.mum.ea.socialmedia.service.PostServiceImpl.deactivatPost(..))",returning = "ret")
    public void afterdeactivatePost(JoinPoint joinPoint, Post ret){
        User u=ret.getUser();
        if(postService.isAbuser(u)==true){
            userService.deactivatUser(u);
            System.out.println("u:"+u.getActive());

        }


    }
}
