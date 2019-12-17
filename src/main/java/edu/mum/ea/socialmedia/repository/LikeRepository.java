package edu.mum.ea.socialmedia.repository;

import edu.mum.ea.socialmedia.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long> {
}
