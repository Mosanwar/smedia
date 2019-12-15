package edu.mum.ea.socialmedia.repository;

import edu.mum.ea.socialmedia.model.Claim;
import edu.mum.ea.socialmedia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ClaimReposotry extends JpaRepository<Claim, Long> {
    Optional<Claim> getClaimById(Long Id);
    @Query("delete  from Claim as claim where claim.user.id=?1")
    void deleteClaimByUser(Long user);
    @Query("select claim from Claim as claim where claim.user.id=?1")
    Optional<Claim>getClaimByUserId(Long userID);
    void removeClaimByUser(User user);

}
