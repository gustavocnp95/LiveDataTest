package com.medium.livedatatest;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.medium.livedatatest.conta.ContaRepositorio;
import com.medium.livedatatest.conta.ContaRepositorioImpl;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CadastroContaViewModel extends ViewModel {
    @NonNull
    private final String TAG = CadastroContaViewModel.class.getSimpleName();
    @NonNull
    public final ContaCadastro mContaCadastro = new ContaCadastro();
    @NonNull
    private final ContaRepositorio mRepositorio = new ContaRepositorioImpl();
    @NonNull
    private final MutableLiveData<Boolean> mContaInseridaLiveData = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<Boolean> mShouldShowProgressDialog = new MutableLiveData<>();
    @NonNull
    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @NonNull
    public ContaCadastro getContaCadastro() {
        return mContaCadastro;
    }

    @NonNull
    public MutableLiveData<Boolean> getContaInseridaLiveData() {
        return mContaInseridaLiveData;
    }

    @NonNull
    public MutableLiveData<Boolean> getShouldShowProgressDialogLiveData() {
        return mShouldShowProgressDialog;
    }

    public void onSaveClick(@NonNull final View view) {
        Log.d(TAG, "Botão salvar clicado.");
        mCompositeDisposable.add(
                mRepositorio.insert(view.getContext(), mContaCadastro.toContaEntity())
                        .delay(5, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(this::onInsertSubscribe)
                        .subscribe(this::onInsertSuccess));
    }

    private void onInsertSubscribe(@NonNull final Disposable disposable) {
        mShouldShowProgressDialog.setValue(true);
    }

    private void onInsertSuccess() {
        mShouldShowProgressDialog.setValue(false);
        mContaInseridaLiveData.setValue(true);
    }
}