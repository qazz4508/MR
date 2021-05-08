package com.zq.jz.ui.fragment;

import com.zq.jz.R;
import com.zq.jz.base.BaseMvpFragment;
import com.zq.jz.base.BasePresenter;

import java.util.List;

public class AddPayFragment extends BaseMvpFragment {

    public static AddPayFragment newInstance() {
        AddPayFragment addPayFragment = new AddPayFragment();
        return addPayFragment;
    }


    @Override
    protected void addPresenter(List<BasePresenter> presenterList) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_pay;
    }
}
