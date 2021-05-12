package com.zq.jz.base;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.annotations.NonNull;

public abstract class BaseDialogFragment extends DialogFragment {
    private Unbinder unBinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(this.getLayoutId(), container, false);
        unBinder = ButterKnife.bind(this, view);
        queueMethod();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme);

        super.onViewCreated(view, savedInstanceState);
    }

    protected void queueMethod() {
        initData();
        initView();
        initListener();
        loadData();
    }

    protected void initData() {

    }

    protected void initView() {

    }

    protected void initListener() {
    }


    protected void loadData() {

    }


    @Override
    public void onDestroyView() {
        if (unBinder != null) {
            unBinder.unbind();
        }

        super.onDestroyView();
    }

    protected abstract int getLayoutId();

}
