package com.zq.jz.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.zq.jz.db.table.UserInComePayType;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface UserInComePayTypeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserInComePayType... types);

    @Query("select * from user_income_pay_type")
    Single<List<UserInComePayType>> getAll();

    @Query("select * from user_income_pay_type where bill_type_id = :type")
    Single<List<UserInComePayType>> getFromType(int type);
}
