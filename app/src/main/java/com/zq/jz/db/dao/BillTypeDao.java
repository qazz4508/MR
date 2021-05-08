package com.zq.jz.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.zq.jz.db.table.BillType;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface BillTypeDao {
    @Query("select * from bill_type")
    Single<List<BillType>> getAll();
}
