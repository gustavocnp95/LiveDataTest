package com.medium.livedatatest.conta;

import android.content.Context;

import androidx.annotation.NonNull;

import io.reactivex.rxjava3.core.Completable;

public interface ContaRepositorio {
    Completable insert(@NonNull final Context context, @NonNull final ContaEntity contaEntity);
}
