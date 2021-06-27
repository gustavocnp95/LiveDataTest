package com.medium.livedatatest;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.medium.livedatatest.common.BaseFragment;
import com.medium.livedatatest.conta.model.ContaTipo;
import com.medium.livedatatest.databinding.FragmentCadastroContaBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class CadastroContaFragment extends BaseFragment {
    private FragmentCadastroContaBinding mBinding;
    @NonNull
    private final Observer<Boolean> mContaInseridaObserver = createContaInseridaObserver();
    @NonNull
    private final Observer<Boolean> mShouldShowProgressDialogObserver
            = createShouldShowProgressDialogObserver();

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_cadastro_conta,
                container,
                false);
        if (getActivity() != null) {
            mBinding.setViewModel(new ViewModelProvider(getActivity()).get(CadastroContaViewModel.class));
            setupTipoDropDownAdapter();
            setupDatePicker();
            setupObservers();
        }
        return mBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBinding = null;
    }

    private void setupTipoDropDownAdapter() {
        mBinding.dropdownTipo.setAdapter(getAdapter());
    }

    private void setupObservers() {
        mBinding.getViewModel().getContaInseridaLiveData().observe(
                getViewLifecycleOwner(), mContaInseridaObserver);
        mBinding.getViewModel().getShouldShowProgressDialogLiveData().observe(getViewLifecycleOwner(), mShouldShowProgressDialogObserver);
    }

    private void setupDatePicker() {
        mBinding.textInputLayoutDateVencimento.setStartIconOnClickListener(
                v -> showDatePickerDialog());
    }

    private void showDatePickerDialog() {
        final MaterialDatePicker datePicker = MaterialDatePicker
                .Builder
                .datePicker()
                .setTitleText("Selecione uma data")
                .build();
        datePicker.show(getActivity().getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
        datePicker.addOnPositiveButtonClickListener(l -> handleSelectedDate(l));
    }

    @SuppressLint("SimpleDateFormat")
    private void handleSelectedDate(Object l) {
        final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        mBinding.textInputEditDateVencimento.setText(df.format(Long.parseLong(l.toString())));
    }

    private ArrayAdapter<ContaTipo> getAdapter() {
        return new ArrayAdapter<>(getContext(),
                R.layout.support_simple_spinner_dropdown_item,
                ContaTipo.values());
    }

    @NonNull
    private Observer<Boolean> createContaInseridaObserver() {
        return contaInserida -> {
            if (contaInserida) {
                Toast.makeText(getContext(), "Conta inserida com sucesso!", Toast.LENGTH_SHORT).show();
                clearFields();
            }
        };
    }

    @NonNull
    private Observer<Boolean> createShouldShowProgressDialogObserver() {
        return shouldShowProgressDialog -> {
            if (shouldShowProgressDialog) {
                showProgressDialog();
            } else {
                hideProgressDialog();
            }
        };
    }

    private void showProgressDialog() {
        mBinding.progress.setVisibility(View.VISIBLE);
    }

    private void hideProgressDialog() {
        mBinding.progress.setVisibility(View.GONE);
    }

    private void clearFields() {
        mBinding.edtTitle.setText("");
        mBinding.dropdownTipo.setText("");
        mBinding.edtAmount.setText("");
        mBinding.textInputEditDateVencimento.setText("");
    }
}
