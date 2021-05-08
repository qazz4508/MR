package com.zq.jz.ui.fragment;

import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zq.jz.MyApplication;
import com.zq.jz.R;
import com.zq.jz.base.BaseMvpFragment;
import com.zq.jz.base.BasePresenter;
import com.zq.jz.bean.BillMultipleItem;
import com.zq.jz.bean.InComeSection;
import com.zq.jz.db.listener.OnDBListener;
import com.zq.jz.bean.HomeCardBean;
import com.zq.jz.ui.activity.AddInComeTypeActivity;
import com.zq.jz.ui.adapter.JzAdapter;
import com.zq.jz.ui.contract.JzContract;
import com.zq.jz.ui.model.DbModel;
import com.zq.jz.ui.presenter.JzPresenter;
import com.zq.jz.util.DBUtil;
import com.zq.jz.util.LogUtil;
import com.zq.jz.util.SpDataManager;
import com.zq.jz.widge.JzHeadView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class JzFragment extends BaseMvpFragment implements JzContract.View {

    @BindView(R.id.rv)
    RecyclerView mRv;

    private JzPresenter mJzPresenter;
    private JzAdapter mJzAdapter;
    private JzHeadView mJzHeadView;

    public static JzFragment newInstance() {
        JzFragment jzFragment = new JzFragment();
        return jzFragment;
    }

    @Override
    protected void addPresenter(List<BasePresenter> presenterList) {
        mJzPresenter = new JzPresenter();
        presenterList.add(mJzPresenter);
    }

    @Override
    protected void initView() {
        mRv.setLayoutManager(new LinearLayoutManager(getContext()));

        mJzAdapter = new JzAdapter(null);
        mJzHeadView = new JzHeadView(getContext());
        mJzAdapter.addHeaderView(mJzHeadView);

        mRv.setAdapter(mJzAdapter);

        DBUtil.copyDbFile(MyApplication.getAppContext(), "jz.db", new DBUtil.OnCopyListener() {
            @Override
            public void onComplete() {
                LogUtil.log("comp");
            }
        });
    }

    @Override
    protected void initListener() {
        mJzHeadView.setOnHeadListener(new JzHeadView.onHeadListener() {
            @Override
            public void onShowDayClick() {
                mJzPresenter.showDayData();
            }

            @Override
            public void onShowMonthClick() {
                mJzPresenter.showMonthData();
            }
        });
    }

    @Override
    protected void loadData() {
        int homeCardType = SpDataManager.getHomeCardType();
        switch (homeCardType) {
            case 0:
                mJzPresenter.showMonthData();
                break;
            case 1:
                mJzPresenter.showDayData();
                break;
        }
        mJzPresenter.showBills();
    }

    @OnClick(R.id.fab_add)
    public void add(){
        Intent intent = new Intent(getContext(), AddInComeTypeActivity.class);
        startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_jz;
    }

    @Override
    public void showMonthData(HomeCardBean dataSet) {
        mJzHeadView.showMonthData(dataSet);
    }

    @Override
    public void showDayData(HomeCardBean dataSet) {
        mJzHeadView.showDayData(dataSet);
    }

    @Override
    public void onGetBillSuccess(List<BillMultipleItem> list) {
        mJzAdapter.setNewData(list);
    }
}
