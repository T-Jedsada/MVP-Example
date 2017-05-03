package com.pondthaitay.mvp.example.ui;

import com.pondthaitay.mvp.example.Calculator;
import com.pondthaitay.mvp.example.base.BasePresenter;

public class MainPresenter extends BasePresenter<MainView.View> implements MainView.Presenter {

    private int resultPlus;
    private Calculator calculator;

    public static MainView.Presenter create() {
        return new MainPresenter(Calculator.newInstance());
    }

    public MainPresenter(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void plus(int x, int y) {
        resultPlus = calculator.plus(x, y);
        getView().setOnResultPlus(resultPlus);
    }

    @Override
    public void minus(int... number) {
        resultPlus = calculator.minus(number[0], number[1]);
        getView().setOnResultPlus(resultPlus);
    }

    @Override
    public int getResultPlus() {
        return resultPlus;
    }
}
