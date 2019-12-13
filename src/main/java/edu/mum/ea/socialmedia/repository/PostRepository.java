package edu.mum.ea.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.ea.socialmedia.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

	
}
