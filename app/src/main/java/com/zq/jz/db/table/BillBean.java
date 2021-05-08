package com.zq.jz.db.table;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BillBean {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "time")
    private long time;

    @ColumnInfo(name = "bill_type")
    private int bill_type;

    @ColumnInfo(name = "money")
    private int money;

    @ColumnInfo(name = "type")
    private int type;

    @ColumnInfo(name = "remark")
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getBill_type() {
        return bill_type;
    }

    public void setBill_type(int bill_type) {
        this.bill_type = bill_type;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
