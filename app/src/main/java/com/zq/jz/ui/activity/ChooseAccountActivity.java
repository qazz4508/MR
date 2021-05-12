package com.zq.jz.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zq.jz.R;
import com.zq.jz.base.BaseMvpActivity;
import com.zq.jz.base.BasePresenter;
import com.zq.jz.bean.AccountChildItem;
import com.zq.jz.bean.AccountExpandItem;
import com.zq.jz.db.table.Account;
import com.zq.jz.db.table.AccountType;
import com.zq.jz.ui.adapter.ChooseAccountAdapter;
import com.zq.jz.ui.contract.ChooseAccountContract;
import com.zq.jz.ui.presenter.ChooseAccountPresenter;
import com.zq.jz.widge.TitleBarView;

import java.util.List;

import butterknife.BindView;

/**
 * 选择账户
 */
public class ChooseAccountActivity extends BaseMvpActivity implements ChooseAccountContract.View {

    @BindView(R.id.title_view)
    TitleBarView mTitleBarView;
    @BindView(R.id.rv)
    RecyclerView mRecyclerView;

    private ChooseAccountPresenter mChooseAccountPresenter;
    private ChooseAccountAdapter mChooseAccountAdapter;

    public static void start(Context context) {
        Intent intent = new Intent(context, ChooseAccountActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void addPresenter(List<BasePresenter> presenterList) {
        mChooseAccountPresenter = new ChooseAccountPresenter();
        presenterList.add(mChooseAccountPresenter);
    }

    @Override
    protected void initView() {
        mTitleBarView.setTitle("");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mChooseAccountAdapter = new ChooseAccountAdapter(null);
        mRecyclerView.setAdapter(mChooseAccountAdapter);
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
        mChooseAccountAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewType = adapter.getItemViewType(position);
                if (itemViewType == AccountExpandItem.TYPE_L0) {
                    AccountExpandItem expandItem = (AccountExpandItem) mChooseAccountAdapter.getData().get(position);
                    if (expandItem.hasSubItem()) {
                        boolean expanded = expandItem.isExpanded();
                        if (expanded) {
                            mChooseAccountAdapter.collapse(position, true);
                        } else {
                            mChooseAccountAdapter.expand(position, true);
                        }
                    } else {
                        AddUserAccountActivity.start(ChooseAccountActivity.this, expandItem.getAccountType(), null);
                    }
                } else {
                    AccountChildItem accountChildItem = (AccountChildItem) mChooseAccountAdapter.getData().get(position);
                    AddUserAccountActivity.start(ChooseAccountActivity.this, null, accountChildItem.getAccount());
                }
            }
        });
    }

    @Override
    protected void loadData() {
        mChooseAccountPresenter.getAccountList();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_account;
    }

    @Override
    public void onGetAccountListSuccess(List<MultiItemEntity> list) {
        mChooseAccountAdapter.setNewData(list);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
    }
}
