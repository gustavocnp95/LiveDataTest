package com.medium.livedatatest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.medium.livedatatest.common.BaseActivity;

public class CadastroContaActivity extends BaseActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_frag_container);
        if (savedInstanceState == null) {
            addFragment(new CadastroContaFragment(),
                    R.id.fragContainer,
                    CadastroContaFragment.class.getSimpleName());
        }
    }

    public static Intent createIntent(@NonNull final Activity currentActivity) {
        return new Intent(currentActivity, CadastroContaActivity.class);
    }
}