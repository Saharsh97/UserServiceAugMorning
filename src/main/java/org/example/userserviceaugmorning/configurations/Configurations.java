package org.example.userserviceaugmorning.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Configurations {

    @Bean
    public BCryptPasswordEncoder createBCryptObject(){
        // ...
        // ,,.
        return new BCryptPasswordEncoder(15);
    }
}
