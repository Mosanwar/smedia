package edu.mum.ea.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.ea.socialmedia.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<User,Long>{

}
