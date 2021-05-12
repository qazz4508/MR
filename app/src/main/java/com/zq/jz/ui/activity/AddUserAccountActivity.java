package com.zq.jz.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import com.zq.jz.R;
import com.zq.jz.base.BaseMvpActivity;
import com.zq.jz.base.BasePresenter;
import com.zq.jz.bean.event.EventAddNewUserAccount;
import com.zq.jz.db.table.Account;
import com.zq.jz.db.table.AccountType;
import com.zq.jz.ui.contract.AddUserAccountContract;
import com.zq.jz.ui.presenter.AddUserAccountPresenter;
import com.zq.jz.widge.TitleBarView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 添加用户账户
 */
public class AddUserAccountActivity extends BaseMvpActivity implements AddUserAccountContract.View {

    @BindView(R.id.tbv)
    TitleBarView mTitleBarView;
    @BindView(R.id.et_acc_name)
    EditText mEtName;
    @BindView(R.id.et_acc_over)
    EditText mEtOver;
    @BindView(R.id.et_remark)
    EditText mEtRemark;
    @BindView(R.id.tv_acc_type_real)
    TextView mTvType;

    private AddUserAccountPresenter mAddUserAccountPresenter;

    public static void start(Context context, AccountType accountType, Account account) {
        Intent intent = new Intent(context, AddUserAccountActivity.class);
        intent.putExtra("at", accountType);
        intent.putExtra("a", account);
        context.startActivity(intent);
    }

    @Override
    protected void addPresenter(List<BasePresenter> presenterList) {
        mAddUserAccountPresenter = new AddUserAccountPresenter();
        presenterList.add(mAddUserAccountPresenter);
    }

    @Override
    protected void initView() {
        mTitleBarView.setTitle(getString(R.string.add_account));
        if (getAccount() == null) {
            mTvType.setText(getAccountType().getName());
        } else {
            mTvType.setText(getAccount().getName());
        }
    }

    @OnClick(R.id.tv_save)
    public void save() {
        mAddUserAccountPresenter.insert();
    }

    @Override
    public void onInsertSuccess() {
        finish();
        EventBus.getDefault().post(new EventAddNewUserAccount());
    }

    @Override
    public String getInputName() {
        return mEtName.getText().toString().trim();
    }

    @Override
    public String getInputOver() {
        return mEtOver.getText().toString().trim();
    }

    @Override
    public String getInputRemark() {
        return mEtRemark.getText().toString().trim();
    }

    @Override
    public AccountType getAccountType() {
        return (AccountType) getIntent().getSerializableExtra("at");
    }

    @Override
    public Account getAccount() {
        return (Account) getIntent().getSerializableExtra("a");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_user_account;
    }
}
