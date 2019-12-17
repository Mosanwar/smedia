package edu.mum.ea.socialmedia.security;

import edu.mum.ea.socialmedia.exception.EmailNotFoundException;
import edu.mum.ea.socialmedia.model.User;
import edu.mum.ea.socialmedia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws EmailNotFoundException {
        User user = userRepository.findByEmail(email)/*.orElseThrow(
                () -> new EmailNotFoundException("User Not Found with -> username or email : " + email))*/;

        return UserPrinciple.build(user);
    }
}
