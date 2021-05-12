package com.zq.jz.widge;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zq.jz.R;

import butterknife.ButterKnife;

public class AssetsHeadView extends FrameLayout {

    public AssetsHeadView(@NonNull Context context) {
        this(context, null);
    }

    public AssetsHeadView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = inflate(context, R.layout.layout_assets_head, this);
        ButterKnife.bind(this, view);
    }


}
