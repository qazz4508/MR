package com.zq.jz.db.table;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "account",
        foreignKeys = {@ForeignKey(entity = AccountType.class,parentColumns = "id",childColumns = "type_id")},
        indices = {@Index(name = "sy_account_type_id",value = "type_id")})
public class Account {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String name;

    @ColumnInfo(name = "type_id")
    private int typeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
