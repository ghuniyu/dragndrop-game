package com.aulia.belajariqra;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PilihActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pilih);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.tvBack)
    void back() {
        finish();
    }

    @OnClick(R.id.ll_belajar)
    void openBelajar() {
        startActivity(new Intent(this, BelajarActivity.class));
    }

    @OnClick(R.id.action_open_main_menu)
    void openMainMenu() {
        Intent intent = new Intent(this, MainMenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
    }

    @OnClick(R.id.ll_bermain)
    void openMenu() {
        GameSave gameSave = Hawk.get("Nama Gua", new GameSave());

        if (gameSave.learningProgress.size() == 30) {
            startActivity(new Intent(this, MenuActivity.class));
        } else {
            Toast.makeText(this, "Belajar Dulu Jancuk", Toast.LENGTH_SHORT).show();
        }
    }
}
