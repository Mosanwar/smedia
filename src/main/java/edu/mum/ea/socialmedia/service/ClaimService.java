package edu.mum.ea.socialmedia.service;

import edu.mum.ea.socialmedia.model.Claim;
import edu.mum.ea.socialmedia.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ClaimService {
    List<Claim> getAllClaim();
    Optional<Claim> getClaimById(Claim claim);
     ResponseEntity addClaim(User user, Claim claim);
    Optional<Claim>getClaimByUserId(long userId);
    Boolean ignoreCalim(long id);


}
