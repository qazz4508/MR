package com.zq.jz.db.table;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_config",
        foreignKeys = {@ForeignKey(entity = UserAccount.class,parentColumns = "id",childColumns = "select_account_id")},
        indices = {@Index(name = "sy_user_config_select_account_id",value = "select_account_id")})
public class UserConfig {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "select_account_id")
    private int selectAccountId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSelectAccountId() {
        return selectAccountId;
    }

    public void setSelectAccountId(int selectAccountId) {
        this.selectAccountId = selectAccountId;
    }

    @Override
    public String toString() {
        return "UserConfig{" +
                "id=" + id +
                ", selectAccountId=" + selectAccountId +
                '}';
    }
}
