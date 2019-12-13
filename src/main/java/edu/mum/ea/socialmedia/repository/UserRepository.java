package edu.mum.ea.socialmedia.repository;

import edu.mum.ea.socialmedia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    User findByEmail(String email);
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);

}
