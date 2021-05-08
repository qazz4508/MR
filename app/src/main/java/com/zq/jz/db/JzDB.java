package com.zq.jz.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.zq.jz.db.dao.BillDao;
import com.zq.jz.db.dao.BillTypeDao;
import com.zq.jz.db.dao.InComeDao;
import com.zq.jz.db.dao.InComeTypeDao;
import com.zq.jz.db.table.BillBean;
import com.zq.jz.db.table.BillType;
import com.zq.jz.db.table.InCome;
import com.zq.jz.db.table.IncomeType;

@Database(entities = {BillType.class, BillBean.class, IncomeType.class, InCome.class}, version = 1, exportSchema = false)
public abstract class JzDB extends RoomDatabase {

    private static volatile JzDB INSTANCE;

    public static JzDB getInstance(Context context) {
        // 若为空则进行实例化
        // 否则直接返回
        if (INSTANCE == null) {
            synchronized (JzDB.class) {
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            JzDB.class, "jz.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract BillDao billDao();
    public abstract BillTypeDao billTypeDao();
    public abstract InComeTypeDao incomeTypeDao();
    public abstract InComeDao incomeDao();
}
