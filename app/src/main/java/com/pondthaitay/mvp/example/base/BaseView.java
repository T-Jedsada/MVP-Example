package com.pondthaitay.mvp.example.base;

public interface BaseView {
    interface View {
        Presenter getPresenter();
    }

    interface Presenter<V extends BaseView.View> {
        void attachView(V mvpView);

        void detachView();

        V getView();

        void onViewCreate();

        void onViewDestroy();

        void onViewStart();

        void onViewStop();
    }
}