package com.zq.jz.widge;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zq.jz.R;
import com.zq.jz.bean.CalculatorMultiBean;
import com.zq.jz.util.CalculatorUtil;
import com.zq.jz.util.SizeUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalculatorView extends FrameLayout {
    @BindView(R.id.rv)
    RecyclerView mRecyclerView;

    private final int mSpace = SizeUtil.dip2px(1);

    private String mResult = "";

    public CalculatorView(@NonNull Context context) {
        this(context, null);
    }

    public CalculatorView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = inflate(context, R.layout.view_calculator_layout, this);
        ButterKnife.bind(this, view);
        initView();
    }


    private void initView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));

        List<CalculatorMultiBean> multiBeanList = getData();

        CalculatorAdapter calculatorAdapter = new CalculatorAdapter(multiBeanList);
        mRecyclerView.setAdapter(calculatorAdapter);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                int position = parent.getChildLayoutPosition(view);
                if (position >= 0 && position <= 3) {
                    outRect.bottom = mSpace;
                } else if (position >= 4 && position <= 7) {
                    outRect.top = mSpace;
                    outRect.bottom = mSpace;
                } else if (position >= 8 && position <= 11) {
                    outRect.top = mSpace;
                    outRect.bottom = mSpace;
                } else if (position >= 12 && position <= 15) {
                    outRect.top = mSpace;
                }
            }
        });

        calculatorAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewType = adapter.getItemViewType(position);
                switch (itemViewType) {
                    case CalculatorMultiBean.TYPE_NUM:
                        mResult += calculatorAdapter.getData().get(position).getText();
                        double cal = CalculatorUtil.cal(mResult);
                        mListener.onResult(mResult, String.valueOf(cal));
                        break;
                    case CalculatorMultiBean.TYPE_POINT:
                        if (mResult.endsWith(".")) {
                            return;
                        }
                        mResult += ".";
                        double calPoint = CalculatorUtil.cal(mResult);
                        mListener.onResult(mResult, String.valueOf(calPoint));
                        break;
                    case CalculatorMultiBean.TYPE_NEW:
                        break;
                    case CalculatorMultiBean.TYPE_ADD:
                        if (mResult.endsWith("+")) {
                            return;
                        }
                        mResult += "+";
                        double calAdd = CalculatorUtil.cal(mResult);
                        mListener.onResult(mResult, String.valueOf(calAdd));
                        break;
                    case CalculatorMultiBean.TYPE_SUB:
                        if (mResult.endsWith("-")) {
                            return;
                        }
                        mResult += "-";
                        double calSub = CalculatorUtil.cal(mResult);
                        mListener.onResult(mResult, String.valueOf(calSub));
                        break;
                    case CalculatorMultiBean.TYPE_DELETE:
                        if (mResult.length() > 0) {
                            mResult = mResult.substring(0, mResult.length() - 1);
                            double calDelete = CalculatorUtil.cal(mResult);
                            mListener.onResult(mResult, String.valueOf(calDelete));
                            return;
                        }
                        mListener.onResult(mResult, mResult);
                        break;
                    case CalculatorMultiBean.TYPE_OK:
                        double calOk = CalculatorUtil.cal(mResult);
                        mListener.onOk(String.valueOf(calOk));
                        break;
                }
            }
        });

        calculatorAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                if (adapter.getItemViewType(position) == CalculatorMultiBean.TYPE_DELETE) {
                    mResult = "";
                    mListener.onResult(mResult, mResult);
                    return true;
                }
                return false;
            }
        });
    }


    private List<CalculatorMultiBean> getData() {
        List<CalculatorMultiBean> list = new ArrayList<>();
        CalculatorMultiBean n1 = new CalculatorMultiBean(CalculatorMultiBean.TYPE_NUM);
        n1.setText("1");
        list.add(n1);
        CalculatorMultiBean n2 = new CalculatorMultiBean(CalculatorMultiBean.TYPE_NUM);
        n2.setText("2");
        list.add(n2);
        CalculatorMultiBean n3 = new CalculatorMultiBean(CalculatorMultiBean.TYPE_NUM);
        n3.setText("3");
        list.add(n3);
        CalculatorMultiBean itemNew = new CalculatorMultiBean(CalculatorMultiBean.TYPE_NEW);
        list.add(itemNew);

        CalculatorMultiBean n4 = new CalculatorMultiBean(CalculatorMultiBean.TYPE_NUM);
        n4.setText("4");
        list.add(n4);
        CalculatorMultiBean n5 = new CalculatorMultiBean(CalculatorMultiBean.TYPE_NUM);
        n5.setText("5");
        list.add(n5);
        CalculatorMultiBean n6 = new CalculatorMultiBean(CalculatorMultiBean.TYPE_NUM);
        n6.setText("6");
        list.add(n6);

        CalculatorMultiBean itemAdd = new CalculatorMultiBean(CalculatorMultiBean.TYPE_ADD);
        itemAdd.setText("+");
        list.add(itemAdd);

        CalculatorMultiBean n7 = new CalculatorMultiBean(CalculatorMultiBean.TYPE_NUM);
        n7.setText("7");
        list.add(n7);
        CalculatorMultiBean n8 = new CalculatorMultiBean(CalculatorMultiBean.TYPE_NUM);
        n8.setText("8");
        list.add(n8);
        CalculatorMultiBean n9 = new CalculatorMultiBean(CalculatorMultiBean.TYPE_NUM);
        n9.setText("9");
        list.add(n9);

        CalculatorMultiBean itemSub = new CalculatorMultiBean(CalculatorMultiBean.TYPE_SUB);
        itemSub.setText("-");
        list.add(itemSub);

        CalculatorMultiBean itemPoint = new CalculatorMultiBean(CalculatorMultiBean.TYPE_POINT);
        itemPoint.setText(".");
        list.add(itemPoint);

        CalculatorMultiBean n0 = new CalculatorMultiBean(CalculatorMultiBean.TYPE_NUM);
        n0.setText("0");
        list.add(n0);

        CalculatorMultiBean itemDelete = new CalculatorMultiBean(CalculatorMultiBean.TYPE_DELETE);
        list.add(itemDelete);

        CalculatorMultiBean itemOk = new CalculatorMultiBean(CalculatorMultiBean.TYPE_OK);
        itemOk.setText("OK");
        list.add(itemOk);

        return list;
    }

    class CalculatorAdapter extends BaseMultiItemQuickAdapter<CalculatorMultiBean, BaseViewHolder> {

        private final int mWidth;
        private final int mHeight;

        public CalculatorAdapter(List<CalculatorMultiBean> data) {
            super(data);
            addItemType(CalculatorMultiBean.TYPE_NUM, R.layout.item_cal_num);
            addItemType(CalculatorMultiBean.TYPE_POINT, R.layout.item_cal_num);
            addItemType(CalculatorMultiBean.TYPE_NEW, R.layout.item_cal_num);
            addItemType(CalculatorMultiBean.TYPE_ADD, R.layout.item_cal_num);
            addItemType(CalculatorMultiBean.TYPE_SUB, R.layout.item_cal_num);
            addItemType(CalculatorMultiBean.TYPE_DELETE, R.layout.item_cal_delete);
            addItemType(CalculatorMultiBean.TYPE_OK, R.layout.item_cal_num);
            DisplayMetrics dm2 = getResources().getDisplayMetrics();
            mWidth = (dm2.widthPixels - mSpace * 6) / 4;
            mHeight = mWidth / 3 * 2;
        }

        @Override
        protected void convert(BaseViewHolder helper, CalculatorMultiBean item) {
            ViewGroup.LayoutParams p = helper.itemView.getLayoutParams();
            p.width = mWidth;
            p.height = mHeight;
            helper.itemView.setLayoutParams(p);
            switch (helper.getItemViewType()) {
                case CalculatorMultiBean.TYPE_NUM:
                case CalculatorMultiBean.TYPE_POINT:
                case CalculatorMultiBean.TYPE_ADD:
                case CalculatorMultiBean.TYPE_SUB:
                case CalculatorMultiBean.TYPE_OK:
                    helper.setText(R.id.tv_text, item.getText());
                    break;
                case CalculatorMultiBean.TYPE_NEW:
                    break;
                case CalculatorMultiBean.TYPE_DELETE:
                    break;
            }
        }
    }

    private OnCalResultListener mListener;

    public void setListener(OnCalResultListener listener) {
        mListener = listener;
    }

    public interface OnCalResultListener {
        void onResult(String processText, String result);

        void onOk(String resule);
    }
}
