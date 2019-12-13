package edu.mum.ea.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.ea.socialmedia.model.User;

public interface ProfileRepository extends JpaRepository<Long, User>{

}
