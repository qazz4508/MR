package com.zq.jz.ui.activity;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zq.jz.R;
import com.zq.jz.base.BaseMvpActivity;
import com.zq.jz.base.BasePresenter;
import com.zq.jz.bean.AccountExpandItem;
import com.zq.jz.ui.adapter.AddAccountAdapter;
import com.zq.jz.ui.contract.AddAccountContract;
import com.zq.jz.ui.presenter.AddAccountPresenter;
import com.zq.jz.util.LogUtil;
import com.zq.jz.widge.TitleBarView;

import java.util.List;

import butterknife.BindView;

public class AddAccountActivity extends BaseMvpActivity implements AddAccountContract.View {

    @BindView(R.id.title_view)
    TitleBarView mTitleBarView;
    @BindView(R.id.rv)
    RecyclerView mRecyclerView;

    private AddAccountPresenter mAddAccountPresenter;
    private AddAccountAdapter mAddAccountAdapter;

    @Override
    protected void addPresenter(List<BasePresenter> presenterList) {
        mAddAccountPresenter = new AddAccountPresenter();
        presenterList.add(mAddAccountPresenter);
    }

    @Override
    protected void initView() {
        mTitleBarView.setTitle("");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAddAccountAdapter = new AddAccountAdapter(null);
        mRecyclerView.setAdapter(mAddAccountAdapter);
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
        mAddAccountAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewType = adapter.getItemViewType(position);
                if (itemViewType == AccountExpandItem.TYPE_L0) {
                    AccountExpandItem expandItem = (AccountExpandItem) mAddAccountAdapter.getData().get(position);
                    if (expandItem.getSubItems().size() > 0) {
                        boolean expandable = mAddAccountAdapter.isExpandable(expandItem);
                        if(expandable){
                            mAddAccountAdapter.collapse(position,true);
                            LogUtil.log("折叠");
                        }else {
                            mAddAccountAdapter.expand(position, true);
                            LogUtil.log("展开");
                        }
                    } else {
                        LogUtil.log("添加 l0");
                    }
                } else {
                    LogUtil.log("添加 l1");
                }
            }
        });
    }

    @Override
    protected void loadData() {
        mAddAccountPresenter.getAccountList();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_account;
    }

    @Override
    public void onGetAccountListSuccess(List<MultiItemEntity> list) {
        mAddAccountAdapter.setNewData(list);
    }
}
