package com.zq.jz.db.table;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_income_pay_type",
        foreignKeys = {@ForeignKey(entity = InComePay.class,parentColumns = "id",childColumns = "type_id"),
        @ForeignKey(entity = BillType.class,parentColumns = "id",childColumns = "bill_type_id")},
        indices = {@Index(name = "sy_user_income_pay_type_type_id",value = "type_id"),@Index(name = "sy_user_income_pay_type_bill_type_id",value = "bill_type_id")})
public class UserInComePayType {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "type_id")
    private int typeId;

    @ColumnInfo(name = "bill_type_id")
    private int billTypeId;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "another_name")
    private String anotherName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getBillTypeId() {
        return billTypeId;
    }

    public void setBillTypeId(int billTypeId) {
        this.billTypeId = billTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnotherName() {
        return anotherName;
    }

    public void setAnotherName(String anotherName) {
        this.anotherName = anotherName;
    }

    @Override
    public String toString() {
        return "UserInComePayType{" +
                "id=" + id +
                ", typeId=" + typeId +
                ", billTypeId=" + billTypeId +
                ", name='" + name + '\'' +
                ", anotherName='" + anotherName + '\'' +
                '}';
    }
}
