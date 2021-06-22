package com.getir.exception;

public class NotEnoughBooksException extends RuntimeException{
    public NotEnoughBooksException(String s) {
        super(s);
    }
}
