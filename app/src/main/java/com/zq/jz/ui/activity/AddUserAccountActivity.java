package com.zq.jz.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import com.zq.jz.R;
import com.zq.jz.base.BaseMvpActivity;
import com.zq.jz.base.BasePresenter;
import com.zq.jz.db.table.Account;
import com.zq.jz.db.table.AccountType;
import com.zq.jz.widge.TitleBarView;

import java.util.List;

import butterknife.BindView;

public class AddUserAccountActivity extends BaseMvpActivity {

    @BindView(R.id.tbv)
    TitleBarView mTitleBarView;
    @BindView(R.id.et_acc_name)
    EditText mEtName;
    @BindView(R.id.et_acc_over)
    EditText mEtOver;
    @BindView(R.id.tv_acc_type_real)
    TextView mTvType;

    public static void start(Context context, AccountType accountType, Account account) {
        Intent intent = new Intent(context, AddUserAccountActivity.class);
        intent.putExtra("at",accountType);
        intent.putExtra("a",account);
        context.startActivity(intent);
    }

    @Override
    protected void addPresenter(List<BasePresenter> presenterList) {

    }

    @Override
    protected void initView() {
        mTitleBarView.setTitle(getString(R.string.add_account));
        if(getAccount()==null){
            mTvType.setText(getAccountType().getName());
        }else {
            mTvType.setText(getAccount().getName());
        }
    }

    public AccountType getAccountType(){
        return (AccountType) getIntent().getSerializableExtra("at");
    }

    public Account getAccount(){
        return (Account) getIntent().getSerializableExtra("a");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_user_account;
    }
}
