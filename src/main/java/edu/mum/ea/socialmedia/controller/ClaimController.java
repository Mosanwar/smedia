package edu.mum.ea.socialmedia.controller;

import edu.mum.ea.socialmedia.model.Claim;
import edu.mum.ea.socialmedia.service.ClaimService;
import edu.mum.ea.socialmedia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class ClaimController {

    private ClaimService claimService;

    private UserService userService;
    @Autowired
    public ClaimController(ClaimService claimService, UserService userService) {
        this.claimService=claimService;
        this.userService=userService;
    }

        @RequestMapping(value = "/ClaimList", method = RequestMethod.GET)
         public List<Claim> getClaimList() {
        return claimService.getAllClaim();

    }
    @RequestMapping(value = "/ignoreClaim", method = RequestMethod.DELETE)
    public Boolean ignoreClaim(Long userId) {

        return claimService.ignoreCalim(userId);
    }
    @RequestMapping(value = "/activateClaim", method = RequestMethod.POST)
    public Boolean activate(Long userId) {
          userService.activate(userId);
          return claimService.ignoreCalim(userId);
    }


}
