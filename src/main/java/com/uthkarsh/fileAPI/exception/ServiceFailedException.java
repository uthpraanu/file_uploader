package com.uthkarsh.fileAPI.exception;

public class ServiceFailedException extends RuntimeException{
    public ServiceFailedException(String message){
        super(message);
    }
}
