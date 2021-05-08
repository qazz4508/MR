package com.zq.jz.db.table;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "income_pay_type",
        foreignKeys = {@ForeignKey(entity = BillType.class,parentColumns = "id",childColumns = "type")},
        indices = {@Index(name = "sy_income_pay_type",value = "type")}
)
public class IncomePayType {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private int type;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Ignore
    @Override
    public String toString() {
        return "IncomePayType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
