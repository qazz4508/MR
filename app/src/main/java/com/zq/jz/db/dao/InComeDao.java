package com.zq.jz.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.zq.jz.db.table.InCome;
import com.zq.jz.db.table.IncomeType;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface InComeDao {
    @Query("select * from income")
    Single<List<InCome>> getAll();

    @Query("select * from income")
    List<InCome> getAllSync();

    @Query("select * from income where id=:id")
    Single<InCome> queryFromId(int id);

    @Query("select * from income where type=:type")
    List<InCome> queryFromIdSync(int type);
}
