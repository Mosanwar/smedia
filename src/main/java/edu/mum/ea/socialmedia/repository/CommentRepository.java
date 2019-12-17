package edu.mum.ea.socialmedia.repository;

import edu.mum.ea.socialmedia.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
