package com.zq.jz.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.zq.jz.db.table.InComePay;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface InComePayDao {
    @Query("select * from income_pay")
    Single<List<InComePay>> getAll();

    @Query("select * from income_pay")
    List<InComePay> getAllSync();

    @Query("select * from income_pay where id=:id")
    Single<InComePay> queryFromId(int id);

    @Query("select * from income_pay where type=:type")
    List<InComePay> queryFromIdSync(int type);
}
