package com.aulia.belajariqra;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelesaiBelajarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_selesai_belajar);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.ok_action)
    void ok() {
        finish();
    }
}
