package com.zq.jz.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.zq.jz.db.table.BillType;
import com.zq.jz.db.table.IncomeType;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface InComeTypeDao {
    @Query("select * from income_type")
    Single<List<IncomeType>> getAll();

    @Query("select * from income_type")
    List<IncomeType> getAllSync();
}
