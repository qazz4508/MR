package com.zq.jz.db.table;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 类型 收入 支出
 */
@Entity(tableName = "bill_type")
public class BillType {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

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

    @Override
    public String toString() {
        return "BillType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
