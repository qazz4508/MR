package com.zq.jz.db.table;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "income_pay_type")
public class IncomePayType {

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

    @Ignore
    @Override
    public String toString() {
        return "IncomeType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
