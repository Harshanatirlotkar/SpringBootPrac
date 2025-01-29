package com.harsh.myrest.CustException;

public class DepNotFoundException extends RuntimeException {

    public DepNotFoundException(String msg) {
        super(msg);
    }
}
