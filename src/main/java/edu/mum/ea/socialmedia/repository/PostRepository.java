package edu.mum.ea.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.ea.socialmedia.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

	
}
