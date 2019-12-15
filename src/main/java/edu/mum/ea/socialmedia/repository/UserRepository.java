package edu.mum.ea.socialmedia.repository;

import edu.mum.ea.socialmedia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    User findByEmail(String email);
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    List<User> findAllByActiveFalse();
    @Query("select user from User as user where user.blocked=true and user.active= false and user.id=?1")
    boolean checkActiveUser(Long userId);

}
