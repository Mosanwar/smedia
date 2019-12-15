package edu.mum.ea.socialmedia.service;

import edu.mum.ea.socialmedia.model.*;
import edu.mum.ea.socialmedia.repository.*;
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
import java.util.regex.Pattern;

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

	@Autowired
	BadWordReposotory badWordReposotory;
	@Autowired
	UserService userService;

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
