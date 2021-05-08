package com.zq.jz.db.listener;

public interface OnDBListener<T> {
    void onSuccess(T t);

    void onError(String msg);

    void onEmpty();
}
