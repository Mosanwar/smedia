package edu.mum.ea.socialmedia.service;

import edu.mum.ea.socialmedia.model.Claim;
import edu.mum.ea.socialmedia.model.User;
import edu.mum.ea.socialmedia.repository.ClaimReposotry;
import edu.mum.ea.socialmedia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClaimServiceImpl implements ClaimService {
    @Autowired
    ClaimReposotry claimReposotry;
    @Autowired
    UserRepository userRepository;
    @Override
    public List<Claim> getAllClaim() {
        return claimReposotry.findAll();
    }

    @Override
    public Optional<Claim> getClaimById(Claim claim) {
        Optional<Claim>claim1=claimReposotry.getClaimById(claim.getId());
        return (claim1.isEmpty())?Optional.empty():claim1;

    }
    public ResponseEntity addClaim(User user, Claim claim) {
        claim.setUser(user);
       Claim c= claimReposotry.save(claim);
       if(c!=null){
           return new ResponseEntity("success", HttpStatus.OK);

       }else{
           return new ResponseEntity("Sorry the claim was't saved", HttpStatus.FORBIDDEN);
       }

    }

    @Override
    public Optional<Claim> getClaimByUserId(long userId) {
        return claimReposotry.getClaimByUserId(userId);
    }
    @Override
    @Transactional

    public Boolean ignoreCalim(long id) {
        User user=userRepository.findById(id).get();
        claimReposotry.removeClaimByUser(user);
        return true;
    }


}
