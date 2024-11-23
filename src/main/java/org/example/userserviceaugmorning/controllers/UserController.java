package org.example.userserviceaugmorning.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.userserviceaugmorning.dtos.*;
import org.example.userserviceaugmorning.dtos.enums.ResponseStatus;
import org.example.userserviceaugmorning.dtos.enums.TokenVerificationStatus;
import org.example.userserviceaugmorning.exception.*;
import org.example.userserviceaugmorning.models.Token;
import org.example.userserviceaugmorning.models.User;
import org.example.userserviceaugmorning.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
// scaler/user/signup
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable("id") Long userId) throws UserNotFoundException {
        User user = userService.getUserById(userId);
        System.out.println("received request for user with Id " + userId);
        UserDTO userDTO = new UserDTO(user);
        return userDTO;
    }

    @PostMapping("/signup")
    public SignUpResponseDTO signUp(@RequestBody SignUpRequestDTO requestDTO) throws UserAlreadyExistsException, JsonProcessingException {
        User user = userService.signUp(requestDTO.getUserName(), requestDTO.getEmail(), requestDTO.getPassword());

        SignUpResponseDTO signUpResponseDTO = new SignUpResponseDTO();
        signUpResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        signUpResponseDTO.setMessage("User " + user.getName() + " signed up successfully.");
        return signUpResponseDTO;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO requestDTO) throws UserNotFoundException, InvalidPasswordException {
        Token token = userService.login(requestDTO.getEmail(), requestDTO.password);

        LoginResponseDTO responseDTO = new LoginResponseDTO();
        responseDTO.setTokenValue(token.getValue());
        responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        return responseDTO;
    }

    @PostMapping("/logout")
    public LogoutResponseDTO logout(@RequestBody LogoutRequestDTO requestDTO) throws UserNotFoundException, TokenAlreadyExpired {
        Token deletedToken = userService.logout(requestDTO.getUserId(), requestDTO.getTokenValue());
        LogoutResponseDTO responseDTO = new LogoutResponseDTO();
        responseDTO.setTokenHasBeenDeleted(deletedToken.isDeleted());
        responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        return responseDTO;
    }

    @PostMapping("/validateToken")
    public ValidateTokenResponseDTO validateToken(@RequestBody ValidateTokenRequestDTO requestDTO) throws TokenAlreadyExpired, InvalidTokenArgument {
        Boolean isValid = userService.validateToken(requestDTO.getTokenValue(), requestDTO.getUserId());
        ValidateTokenResponseDTO responseDTO = new ValidateTokenResponseDTO();
        responseDTO.setTokenVerificationStatus(isValid == true ? TokenVerificationStatus.VERIFIED : TokenVerificationStatus.MALICIOUS);
        responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        return responseDTO;
    }
}
