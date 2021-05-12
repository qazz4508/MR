package com.zq.jz.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.zq.jz.db.table.Account;
import com.zq.jz.db.table.UserAccount;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface UserAccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserAccount... userAccounts);

    @Query("select * from user_account")
    List<UserAccount> getAll();
}
