package com.zq.jz.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.zq.jz.db.table.Account;
import com.zq.jz.db.table.UserConfig;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface UserConfigDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserConfig... userConfigs);

    @Query("select * from user_config limit 1")
    Single<UserConfig> get();

    @Query("select * from user_config limit 1")
    UserConfig getSync();

    @Update
    void update(UserConfig userConfig);
}
