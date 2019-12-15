package edu.mum.ea.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.ea.socialmedia.model.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    Optional<Post> getPostById(long Id);
    @Query("select count (p.id) from Post as p where p.disabled=true and p.user.id=?1")
    Long countByDisabledTruePerUser(Long userId);
    List<Post> getAllByMaliciousIsTrue();

}
