package com.zq.jz.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.zq.jz.db.table.Account;
import com.zq.jz.db.table.AccountType;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Account... types);

    @Query("select * from account")
    Single<List<Account>> getAll();

    @Query("select * from account where type_id = :typeId")
    List<Account> getFormType(int typeId);
}
