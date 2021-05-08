package com.zq.jz.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.zq.jz.db.table.IncomePayType;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface InComePayTypeDao {
    @Query("select * from income_pay_type")
    Single<List<IncomePayType>> getAll();

    @Query("select * from income_pay_type")
    List<IncomePayType> getAllSync();

    @Query("select * from income_pay_type where type = 2")
    List<IncomePayType> getInComeSync();

    @Query("select * from income_pay_type where type = 1")
    List<IncomePayType> getPaySync();

    @Query("select * from income_pay_type where type = :type")
    List<IncomePayType> getFromType(int type);
}
