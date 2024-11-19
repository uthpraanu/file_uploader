package com.uthkarsh.fileAPI.exception;

public class ServiceFailedException extends RuntimeException{
    public ServiceFailedException(String message){
        super(message);
    }

    public ServiceFailedException(String message, Throwable cause){
        super(message, cause);
    }
}
