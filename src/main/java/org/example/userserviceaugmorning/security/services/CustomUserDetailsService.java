package org.example.userserviceaugmorning.security.services;

import org.example.userserviceaugmorning.models.User;
import org.example.userserviceaugmorning.repositories.UserRepository;

import org.example.userserviceaugmorning.security.models.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(username);
        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException("user with email : " + username + " does not exist");
        }

        User user = userOptional.get();
        UserDetails userDetails = new CustomUserDetails(user);
        return userDetails;
    }
}