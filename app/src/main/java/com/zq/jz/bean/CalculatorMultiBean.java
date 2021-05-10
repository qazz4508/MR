package com.zq.jz.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class CalculatorMultiBean implements MultiItemEntity {
    public static final int TYPE_NUM = 0;
    public static final int TYPE_POINT = 1;
    public static final int TYPE_NEW = 2;
    public static final int TYPE_ADD = 3;
    public static final int TYPE_SUB = 4;
    public static final int TYPE_DELETE = 5;
    public static final int TYPE_OK = 6;

    private int type;
    private String text;

    public CalculatorMultiBean(int type) {
        this.type = type;
    }

    @Override
    public int getItemType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNumInt() {
        if (type == TYPE_NUM) {
            return Integer.parseInt(text);
        }
        throw new RuntimeException("非数字");
    }

    @Override
    public String toString() {
        return "CalculatorMultiBean{" +
                "type=" + type +
                ", text='" + text + '\'' +
                '}';
    }
}
