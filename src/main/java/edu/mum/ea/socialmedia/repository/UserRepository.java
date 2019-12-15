package edu.mum.ea.socialmedia.repository;

import edu.mum.ea.socialmedia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Query("select U.followings from User U where U.email = :email ")
    List<User> findFollowongByEmail(String email);

    //@Query("delete from User.followings f where f.email  and U.email = :userEmail ")
    //void deleteFollowingByEmail(String userEmail,String followingEmail);

    Boolean existsByEmail(String email);
    List<User> findAllByActiveFalse();
    @Query("select user from User as user where user.blocked=true and user.active= false and user.id=?1")
    boolean checkActiveUser(Long userId);
}
