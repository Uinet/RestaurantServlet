package com.github.uinet.exception;

public class UserException extends Exception{
    String text;
    public UserException(String text) {
        this.text=text;
    }
    public String toString(){
        return (text) ;
    }
}
