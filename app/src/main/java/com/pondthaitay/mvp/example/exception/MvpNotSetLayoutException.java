package com.pondthaitay.mvp.example.exception;

public class MvpNotSetLayoutException extends RuntimeException {

    public MvpNotSetLayoutException() {
        super( "getLayoutView() not return 0" );
    }
}
