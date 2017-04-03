package com.aulia.belajariqra;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_menu);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.action_exit)
    void exit() {
        finish();
    }

    @OnClick(R.id.ll_menu)
    void openPilih() {
        startActivity(new Intent(this, PilihActivity.class));
    }
}
