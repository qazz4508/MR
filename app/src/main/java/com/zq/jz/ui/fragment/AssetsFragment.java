package com.zq.jz.ui.fragment;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zq.jz.R;
import com.zq.jz.base.BaseMvpFragment;
import com.zq.jz.base.BasePresenter;
import com.zq.jz.bean.AccountBean;
import com.zq.jz.bean.event.EventAddNewUserAccount;
import com.zq.jz.ui.activity.ChooseAccountActivity;
import com.zq.jz.ui.adapter.AssetsAdapter;
import com.zq.jz.ui.contract.AssetsContract;
import com.zq.jz.ui.presenter.AssetsPresenter;
import com.zq.jz.util.LogUtil;
import com.zq.jz.util.SizeUtil;
import com.zq.jz.widge.AssetsHeadView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AssetsFragment extends BaseMvpFragment implements AssetsContract.View {

    @BindView(R.id.rv)
    RecyclerView mRecyclerView;

    private AssetsPresenter mAssetsPresenter;
    private AssetsAdapter mAssetsAdapter;

    public static AssetsFragment newInstance() {
        AssetsFragment assetsFragment = new AssetsFragment();
        return assetsFragment;
    }

    @Override
    protected void addPresenter(List<BasePresenter> presenterList) {
        mAssetsPresenter = new AssetsPresenter();
        presenterList.add(mAssetsPresenter);
    }

    @Override
    protected void initView() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                int position = parent.getChildLayoutPosition(view);
                int itemViewType = parent.getAdapter().getItemViewType(position);
                if (itemViewType == 0) {
                    outRect.top = SizeUtil.dip2px(10);
                }
            }
        });

        mAssetsAdapter = new AssetsAdapter(null);
        AssetsHeadView assetsHeadView = new AssetsHeadView(getContext());
        mAssetsAdapter.addHeaderView(assetsHeadView);

        mRecyclerView.setAdapter(mAssetsAdapter);
    }

    @Override
    protected void loadData() {
        mAssetsPresenter.getUserAccount();
    }

    @OnClick(R.id.iv_add_account)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_add_account:
                ChooseAccountActivity.start(getContext());
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventAddNewAccount(EventAddNewUserAccount eventAddNewUserAccount) {
        loadData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_assets;
    }

    @Override
    public void onGetUserAccountSuccess(List<AccountBean> list) {
        mAssetsAdapter.setNewData(list);
    }
}
