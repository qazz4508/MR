package com.zq.jz.db.table;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "income",
        foreignKeys = {@ForeignKey(entity = IncomeType.class, parentColumns = "id", childColumns = "type")},
        indices = {@Index(name = "sy_income_type",unique = false,value = {"type"})}
)
public class InCome {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
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

    @Override
    public String toString() {
        return "InCome{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
