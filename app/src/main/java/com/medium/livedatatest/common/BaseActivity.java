package com.medium.livedatatest.common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class BaseActivity extends AppCompatActivity {
    protected void addFragment(final Fragment fragment, final int containerId, final String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(containerId, fragment, tag)
                .commit();
    }
}
