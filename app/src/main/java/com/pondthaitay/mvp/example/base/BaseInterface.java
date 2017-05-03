package com.pondthaitay.mvp.example.base;

public interface BaseInterface {
    interface View {
        Presenter getPresenter();

        void showProgressDialog();

        void hideProgressDialog();
    }

    interface Presenter<V extends BaseInterface.View> {
        void attachView(V mvpView);

        void detachView();

        V getView();

        void onViewCreate();

        void onViewDestroy();

        void onViewStart();

        void onViewStop();
    }
}