package com.medium.livedatatest.conta;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Insert;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface ContaDao {
    @Insert
    Completable insert(@NonNull final ContaEntity contaEntity);
}
