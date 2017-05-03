package com.pondthaitay.mvp.example.base;

import com.pondthaitay.mvp.example.exception.MvpViewNotAttachedException;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends BaseInterface.View>
        implements BaseInterface.Presenter<V> {

    // View reference. We use as a WeakReference
    // because the Activity could be destroyed at any time
    // and we don't want to create a memory leak
    private WeakReference<V> mMvpView;

    @Override
    public void attachView(V mvpView) {
        mMvpView = new WeakReference<>(mvpView);
    }


    @Override
    public void detachView() {
        mMvpView = null;
    }

    @Override
    public V getView() throws NullPointerException {
        if (mMvpView != null) return mMvpView.get();
        throw new MvpViewNotAttachedException();
    }

    @Override
    public void onViewCreate() {
    }


    @Override
    public void onViewStart() {
    }

    @Override
    public void onViewStop() {
    }

    @Override
    public void onViewDestroy() {
    }
}