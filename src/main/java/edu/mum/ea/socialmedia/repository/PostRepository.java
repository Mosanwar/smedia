package edu.mum.ea.socialmedia.repository;

import edu.mum.ea.socialmedia.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.ea.socialmedia.model.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select P from Post P where P.user.email = :email order by P.createdDate DESC ")
    Page<Post> findAllPostsWithoutFollowers(Pageable pageable, String email);

    @Query("select P from Post P join P.user U where U in (select U from User U join U.followers f where f.email = :email) or U.email = :email order by P.createdDate desc ")
    Page<Post> findAllPostsWithFollowers(Pageable pageable, String email);

    @Query("select P from Post P where P.user.email = :email and P.body like %:searchTxt% ")
    Page<Post> searchAllPostsWithoutFollowers(Pageable pageable, String email, String searchTxt);

    @Query("select P from Post P join P.user U where  P.body like %:searchTxt% and (U in (select U from User U join U.followers f where f.email = :email) or U.email = :email )")
    Page<Post> searchAllPostsWithFollowers(Pageable pageable, String email, String searchTxt);

    Optional<Post> getPostById(long Id);

    @Query("select count (p.id) from Post as p where p.disabled=true and p.user.id=?1")
    Long countByDisabledTruePerUser(Long userId);

    @Query("select p from Post as p where p.malicious=true and (p.disabled=false or p.disabled is null)")
    List<Post> getAllByMaliciousIsTrueAndDisabledIsFalse();
}