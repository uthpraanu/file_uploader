package com.uthkarsh.fileAPI.exception;

public class PersonalizedFactoryException extends RuntimeException{
    public PersonalizedFactoryException(String message){
        super(message);
    }

    public PersonalizedFactoryException(String message, Throwable cause){
        super(message, cause);
    }
}
