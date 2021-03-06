package com.zq.jz.ui.activity;

import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zq.jz.R;
import com.zq.jz.base.BaseMvpActivity;
import com.zq.jz.base.BasePresenter;
import com.zq.jz.bean.InComeSection;
import com.zq.jz.db.table.InCome;
import com.zq.jz.ui.adapter.AddInComeTypeAdapter;
import com.zq.jz.ui.contract.AddInComeTypeContract;
import com.zq.jz.ui.presenter.AddInComeTypePresenter;
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
    private AddInComeTypePresenter mAddInComeTypePresenter;

    @Override
    protected void addPresenter(List<BasePresenter> presenterList) {
        mAddInComeTypePresenter = new AddInComeTypePresenter();
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

            }
        });
        mAddInComeTypeAdapter.setOnTypeClickListener(new AddInComeTypeAdapter.OnTypeClickListener() {
            @Override
            public void onTypeClick(int position, InCome inCome) {
                mEditText.setText(inCome.getName());
                mEditText.setSelection(inCome.getName().length());
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
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.fitsSystemWindows(true).init();
    }
}
