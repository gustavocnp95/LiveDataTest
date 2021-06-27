package com.medium.livedatatest.conta;

import android.content.Context;

import androidx.annotation.NonNull;

import com.medium.livedatatest.AppDatabase;

import io.reactivex.rxjava3.core.Completable;

public class ContaRepositorioImpl implements ContaRepositorio {
    @Override
    public Completable insert(@NonNull final Context context,
                              @NonNull final ContaEntity contaEntity) {
        return AppDatabase.getInstance(context).contaDao().insert(contaEntity);
    }
}
