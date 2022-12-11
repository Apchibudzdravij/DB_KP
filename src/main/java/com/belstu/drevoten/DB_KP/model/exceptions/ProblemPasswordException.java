package com.belstu.drevoten.DB_KP.model.exceptions;

public class ProblemPasswordException extends Exception{
    String message;

    public ProblemPasswordException(String str) {
        message=str;
    }

    public String toString(){
        return ("PPE Occurred: "+message) ;
    }
}