package com.zq.jz.ui.dialog;

import android.view.Gravity;
import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zq.jz.R;
import com.zq.jz.base.BaseMvpDialogFragment;
import com.zq.jz.base.BasePresenter;
import com.zq.jz.bean.AccountBean;
import com.zq.jz.ui.adapter.AccountSelectAdapter;
import com.zq.jz.ui.contract.AccountSelectContract;
import com.zq.jz.ui.presenter.AccountSelectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AccountSelectDialog extends BaseMvpDialogFragment implements AccountSelectContract.View {

    @BindView(R.id.rv)
    RecyclerView mRecyclerView;
    private AccountSelectAdapter mAccountSelectAdapter;
    private AccountSelectPresenter mAccountSelectPresenter;
    private OnDialogListener mOnDialogListener;

    public static void show(FragmentManager fragmentManager,OnDialogListener listener) {
        AccountSelectDialog accountSelectDialog = new AccountSelectDialog();
        accountSelectDialog.setOnDialogListener(listener);
        accountSelectDialog.show(fragmentManager, AccountSelectDialog.class.getName());
    }

    @Override
    protected void createPresenter(List<BasePresenter> mPresenters) {
        mAccountSelectPresenter = new AccountSelectPresenter();
        mPresenters.add(mAccountSelectPresenter);
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAccountSelectAdapter = new AccountSelectAdapter(null);
        mRecyclerView.setAdapter(mAccountSelectAdapter);
    }

    @Override
    protected void initListener() {
        mAccountSelectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AccountBean accountBean = mAccountSelectAdapter.getData().get(position);
                if (mOnDialogListener != null) {
                    mOnDialogListener.onSelect(accountBean);
                    dismissAllowingStateLoss();
                }
            }
        });
    }

    @Override
    protected void loadData() {
        mAccountSelectPresenter.getAccountList();
    }

    @OnClick(R.id.tv_cancel)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                dismissAllowingStateLoss();
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_account_select;
    }

    @Override
    public void onGetAccountSuccess(List<AccountBean> list) {
        mAccountSelectAdapter.setNewData(list);
    }

    @Override
    protected int getGravity() {
        return Gravity.BOTTOM;
    }

    public void setOnDialogListener(OnDialogListener onDialogListener) {
        mOnDialogListener = onDialogListener;
    }

    public interface OnDialogListener {
        void onSelect(AccountBean accountBean);
    }
}
