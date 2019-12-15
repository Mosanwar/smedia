package edu.mum.ea.socialmedia.service;

import edu.mum.ea.socialmedia.model.Comment;
import edu.mum.ea.socialmedia.model.Like;
import edu.mum.ea.socialmedia.model.Post;
import edu.mum.ea.socialmedia.model.User;
import edu.mum.ea.socialmedia.repository.CommentRepository;
import edu.mum.ea.socialmedia.repository.LikeRepository;
import edu.mum.ea.socialmedia.repository.PostRepository;
import edu.mum.ea.socialmedia.repository.UserRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Setter
@Getter
@NoArgsConstructor
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Override
    public Page<Post> getAllPostsWithFollowers(String userEmail, int pageNo) {
        return postRepository.findAllPostsWithFollowers(PageRequest.of(pageNo,10),userEmail);
    }

    @Override
    public Page<Post> searchAllPostsWithFollowers(String userEmail, int pageNo, String searchTxt) {
        return postRepository.searchAllPostsWithFollowers(PageRequest.of(pageNo,10),userEmail,searchTxt);
    }

    @Override
    public void addComment(int postId, String comment) {

        Post post = postRepository.getOne(Long.valueOf(postId));
        //TODO get actual user
        User user = userRepository.findByEmail("ahmed@gmail.com");
        Comment comment1 = new Comment();
        comment1.setBody(comment);
        comment1.setUser(user);
        comment1.setPost(post);
        comment1 = commentRepository.save(comment1);
        Set<Comment> commentSet = post.getComments();
        commentSet.add(comment1);
        post.setComments(commentSet);
        postRepository.save(post);
    }
    public void addLike(int postId) {

        Post post = postRepository.getOne(Long.valueOf(postId));
        //TODO get actual user
        User user = userRepository.findByEmail("ahmed@gmail.com");
        Like like = new Like();
        like.setUser(user);
        like.setPost(post);
        like = likeRepository.save(like);
        Set<Like> likeSet = post.getLikes();
        likeSet.add(like);
        post.setLikes(likeSet);
        postRepository.save(post);
    }

    public void removeLike(int postId) {

        Post post = postRepository.getOne(Long.valueOf(postId));
        //TODO get actual user
        User user = userRepository.findByEmail("ahmed@gmail.com");
        Like like = post.getLikes().stream().filter(L -> user.equals(L.getUser()))
                .findAny()
                .orElse(null);
        likeRepository.delete(like);
        Set<Like> likeSet = post.getLikes();
        likeSet.remove(like);
        post.setLikes(likeSet);
        postRepository.save(post);
    }

    @Override
    public Page<Post> getAllPosts(String userEmail,int pageNo) {
        return postRepository.findAllPostsWithoutFollowers(PageRequest.of(pageNo,10), userEmail);
    }

    @Override
    public Page<Post> searchAllPosts(String userEmail, int pageNo, String searchTxt) {
        return postRepository.searchAllPostsWithoutFollowers(PageRequest.of(pageNo,10), userEmail,searchTxt);
    }


}
