package org.example.userserviceaugmorning.exception;

public class TokenAlreadyExpired extends Exception{
    public TokenAlreadyExpired(String message) {
        super(message);
    }
}
