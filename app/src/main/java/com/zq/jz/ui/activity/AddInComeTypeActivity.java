package com.zq.jz.ui.activity;

import android.widget.EditText;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zq.jz.R;
import com.zq.jz.base.BaseMvpActivity;
import com.zq.jz.base.BasePresenter;
import com.zq.jz.bean.InComeSection;
import com.zq.jz.db.table.InComePay;
import com.zq.jz.ui.adapter.AddInComeTypeAdapter;
import com.zq.jz.ui.contract.AddInComeTypeContract;
import com.zq.jz.ui.presenter.AddInComePayTypePresenter;
import com.zq.jz.widge.TitleBarView;

import java.util.List;

import butterknife.BindView;

public class AddInComeTypeActivity extends BaseMvpActivity implements AddInComeTypeContract.View {

    @BindView(R.id.rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.tbv)
    TitleBarView mTitleBarView;
    @BindView(R.id.et_select)
    EditText mEditText;

    private AddInComeTypeAdapter mAddInComeTypeAdapter;
    private AddInComePayTypePresenter mAddInComeTypePresenter;

    @Override
    protected void addPresenter(List<BasePresenter> presenterList) {
        mAddInComeTypePresenter = new AddInComePayTypePresenter();
        presenterList.add(mAddInComeTypePresenter);
    }

    @Override
    protected void initView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int itemViewType = mRecyclerView.getAdapter().getItemViewType(position);
                if (itemViewType == 0) {
                    return 1;
                } else {
                    return 5;
                }
            }
        });
        mRecyclerView.setLayoutManager(gridLayoutManager);

        mAddInComeTypeAdapter = new AddInComeTypeAdapter(null);
        mRecyclerView.setAdapter(mAddInComeTypeAdapter);
    }

    @Override
    protected void initListener() {
        mTitleBarView.setOnBackListener(new TitleBarView.OnBarListener() {
            @Override
            public void onBack() {
                finish();
            }

            @Override
            public void onRight() {
                mAddInComeTypePresenter.insert(mAddInComeTypeAdapter.getSelect());
            }
        });
        mAddInComeTypeAdapter.setOnTypeClickListener(new AddInComeTypeAdapter.OnTypeClickListener() {
            @Override
            public void onTypeClick(int position, InComePay inComePay) {
                mEditText.setText(inComePay.getName());
                mEditText.setSelection(inComePay.getName().length());
            }
        });
    }

    @Override
    protected void loadData() {
        mAddInComeTypePresenter.showTypes();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_income_type;
    }

    @Override
    public void onGetTypeSuccess(List<InComeSection> list) {
        mAddInComeTypeAdapter.setData(list);
    }

    @Override
    public void onInsertSuccess() {
        finish();
    }

    @Override
    public String getAnotherName() {
        return mEditText.getText().toString().trim();
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.fitsSystemWindows(true).init();
    }
}
