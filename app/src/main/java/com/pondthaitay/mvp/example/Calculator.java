package com.pondthaitay.mvp.example;

public class Calculator {

    private static Calculator instance;

    public static Calculator newInstance() {
        if (instance == null)
            instance = new Calculator();
        return instance;
    }

    public int plus(int x, int y) {
        return x + y;
    }

    public int minus(int x, int y) {
        return x - y;
    }
}
