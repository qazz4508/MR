package com.zq.jz.base;

import java.lang.ref.WeakReference;

public class BasePresenter<V extends BaseView> {
    protected WeakReference<V> mViewReference;

    public void attachView(V view) {
        mViewReference = new WeakReference<>(view);
    }

    public void detachView() {
        if (mViewReference != null) {
            mViewReference.clear();
            mViewReference = null;
        }
    }

    public V getView() {
        if (mViewReference != null) {
            return mViewReference.get();
        }
        return null;
    }
}
