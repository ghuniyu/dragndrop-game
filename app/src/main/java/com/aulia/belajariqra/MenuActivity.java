package com.aulia.belajariqra;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.tvBack)
    void back() {
        finish();
    }

    @OnClick(R.id.ll_iqra1)
    void openGame1() {
        startActivity(new Intent(this, Game1Activity.class));
    }

    @OnClick(R.id.action_open_main_menu)
    void openMainMenu() {
        Intent intent = new Intent(this, MainMenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
    }
}
