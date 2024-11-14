package org.example.userserviceaugmorning.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.userserviceaugmorning.exception.*;
import org.example.userserviceaugmorning.models.Token;
import org.example.userserviceaugmorning.models.User;
import org.example.userserviceaugmorning.repositories.TokenRepository;
import org.example.userserviceaugmorning.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private TokenRepository tokenRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, TokenRepository tokenRepository, BCryptPasswordEncoder bCrypt) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.bCryptPasswordEncoder = bCrypt;
    }

    public User getUserById(Long userId) throws UserNotFoundException {
        // do all the checks
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User not found");
        }

        // do the core logic
        User savedUser = optionalUser.get();
        return savedUser;
    }

    public User signUp(String name, String email, String password) throws UserAlreadyExistsException {
        // perform all checks
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent()){
            throw new UserAlreadyExistsException("User already exists");
        }

        // do the core logic.
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));

        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public Token login(String email, String password) throws UserNotFoundException, InvalidPasswordException {
        // do all the checks
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User with email " + email + " not found");
        }
        User user = optionalUser.get();

        if(!bCryptPasswordEncoder.matches(password, user.getHashedPassword())){
            throw new InvalidPasswordException("Password is incorrect");
        }

        // do the core logic
        Token token = new Token();

        String tokenValue = RandomStringUtils.randomAlphanumeric(30);
        token.setValue(tokenValue);

        token.setUser(user);

        // set expiry as next month
        Calendar calendar = Calendar.getInstance(); // automatically contains current date
        calendar.add(Calendar.DATE, 30);
        Date datePlus30Days = calendar.getTime();
        token.setExpiryDate(datePlus30Days);

        Token savedToken = tokenRepository.save(token);
        return savedToken;
    }

    public Token logout(Long userId, String tokenValue) throws UserNotFoundException, TokenAlreadyExpired {
        // do all the checks
        Optional<Token> optionalToken = tokenRepository.findByUserIdAndExpiryDateGreaterThanAndDeleted(userId, new Date(), false);
        if(optionalToken.isEmpty()){
            throw new TokenAlreadyExpired("token is already expired");
        }
        Token token = optionalToken.get();

        // do the core logic
        token.setDeleted(true);
        Token deletedToken = tokenRepository.save(token);
        return deletedToken;
    }

    public boolean validateToken(String tokenValue, Long userId) throws TokenAlreadyExpired, InvalidTokenArgument {
        Optional<Token> optionalToken = tokenRepository.findByUserIdAndExpiryDateGreaterThanAndDeleted(userId, new Date(), false);
        if(optionalToken.isEmpty()){
            throw new TokenAlreadyExpired("token is already expired");
        }
        Token tokenInDB = optionalToken.get();

        if(!tokenInDB.equals(tokenValue)){
            throw new InvalidTokenArgument("wrong token passed");
        }
        return true;
    }
}
