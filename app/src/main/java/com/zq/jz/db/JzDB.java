package com.zq.jz.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.zq.jz.db.dao.BillDao;
import com.zq.jz.db.dao.BillTypeDao;
import com.zq.jz.db.dao.InComePayDao;
import com.zq.jz.db.dao.InComePayTypeDao;
import com.zq.jz.db.table.BillBean;
import com.zq.jz.db.table.BillType;
import com.zq.jz.db.table.InComePay;
import com.zq.jz.db.table.IncomePayType;

@Database(entities = {BillType.class, BillBean.class, IncomePayType.class, InComePay.class}, version = 1, exportSchema = false)
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
    public abstract BillTypeDao getBillTypeDao();
    public abstract InComePayTypeDao getInComePayTypeDao();
    public abstract InComePayDao getInComePayDao();
}
