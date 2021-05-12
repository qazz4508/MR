package com.zq.jz.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.zq.jz.db.table.AccountType;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface AccountTypeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AccountType... types);

    @Query("select * from account_type")
    List<AccountType> getAll();
}
