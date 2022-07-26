package com.example.projeto.model.Service.exceptions;

import lombok.extern.java.Log;
import org.apache.commons.logging.LogFactory;

public class ObjectNotFoundExeption extends RuntimeException{
    private  static final long serialVersionUID = 1L;

    public ObjectNotFoundExeption(String message){
        super(message);
    }

    public ObjectNotFoundExeption(String message, Throwable cause){
        super(message, cause);
    }
}
