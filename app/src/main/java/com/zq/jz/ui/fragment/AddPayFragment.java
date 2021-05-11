package com.zq.jz.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zq.jz.R;
import com.zq.jz.base.BaseMvpFragment;
import com.zq.jz.base.BasePresenter;
import com.zq.jz.bean.UserIncomePayTypeMultipleItem;
import com.zq.jz.bean.event.EventUserIncomePaySelect;
import com.zq.jz.bean.event.EventUserTypeScroll;
import com.zq.jz.db.table.UserInComePayType;
import com.zq.jz.ui.activity.AddInComeTypeActivity;
import com.zq.jz.ui.activity.AddPayTypeActivity;
import com.zq.jz.ui.adapter.UserIncomePayTypeAdapter;
import com.zq.jz.ui.contract.UserIncomePayTypeContract;
import com.zq.jz.ui.presenter.UserIncomePayTypePresenter;
import com.zq.jz.util.LogUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;

public class AddPayFragment extends BaseMvpFragment implements UserIncomePayTypeContract.View {

    @BindView(R.id.rv)
    RecyclerView mRecyclerView;
    private UserIncomePayTypeAdapter mUserIncomePayTypeAdapter;
    private UserIncomePayTypePresenter mUserIncomePayTypePresenter;
    private boolean mTouch;

    public static AddPayFragment newInstance(int pageType) {
        AddPayFragment addPayFragment = new AddPayFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("page_type", pageType);
        addPayFragment.setArguments(bundle);
        return addPayFragment;
    }

    @Override
    protected void addPresenter(List<BasePresenter> presenterList) {
        mUserIncomePayTypePresenter = new UserIncomePayTypePresenter();
        presenterList.add(mUserIncomePayTypePresenter);
    }

    @Override
    protected void initView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 5);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mUserIncomePayTypeAdapter = new UserIncomePayTypeAdapter(null);
        mRecyclerView.setAdapter(mUserIncomePayTypeAdapter);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initListener() {
        mUserIncomePayTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewType = adapter.getItemViewType(position);
                switch (itemViewType) {
                    case UserIncomePayTypeMultipleItem.TYPE_ADD:
                        if (getPageType() == 1) {
                            AddPayTypeActivity.start(getContext());
                        } else {
                            AddInComeTypeActivity.start(getContext());
                        }
                        break;
                    case UserIncomePayTypeMultipleItem.TYPE_TYPE:
                        mUserIncomePayTypeAdapter.setSelectPosition(position);
                        EventBus.getDefault().post(new EventUserIncomePaySelect(getCurrType()));
                        break;
                }
            }
        });

        GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
                public void onShowPress(MotionEvent e) {
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                mTouch = true;
                EventBus.getDefault().post(new EventUserTypeScroll());
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });


        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){
                    mTouch = false;
                    return true;
                }else {
                    if(mTouch){
                        return true;
                    }
                    return gestureDetector.onTouchEvent(event);
                }
            }
        });
    }


    @Override
    protected void loadData() {
        mUserIncomePayTypePresenter.getType();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_pay;
    }

    @Override
    public void onGetTypeSuccess(List<UserIncomePayTypeMultipleItem> list) {
        mUserIncomePayTypeAdapter.setNewData(list);
        EventBus.getDefault().post(new EventUserIncomePaySelect(getCurrType()));
    }

    public void reloadData() {
        if (mUserIncomePayTypePresenter != null) {
            mUserIncomePayTypePresenter.getType();
        }
    }

    public UserInComePayType getCurrType() {
        if (mUserIncomePayTypeAdapter != null) {
            return mUserIncomePayTypeAdapter.getSelectItem();
        }
        return null;
    }

    @Override
    public int getPageType() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            int page_type = arguments.getInt("page_type", 1);
            return page_type;
        }
        return 1;
    }
}
