package com.zq.jz.db.table;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 用户账户
 */
@Entity(tableName = "user_account")
public class UserAccount {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private double over;

    private String remark;

    @ColumnInfo(name = "account_type_pid")
    private int accountTypePid;

    @ColumnInfo(name = "account_type_cid")
    private int accountTypeCid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getAccountTypePid() {
        return accountTypePid;
    }

    public void setAccountTypePid(int accountTypePid) {
        this.accountTypePid = accountTypePid;
    }

    public int getAccountTypeCid() {
        return accountTypeCid;
    }

    public void setAccountTypeCid(int accountTypeCid) {
        this.accountTypeCid = accountTypeCid;
    }

    public double getOver() {
        return over;
    }

    public void setOver(double over) {
        this.over = over;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", over=" + over +
                ", remark='" + remark + '\'' +
                ", accountTypePid=" + accountTypePid +
                ", accountTypeCid=" + accountTypeCid +
                '}';
    }
}
