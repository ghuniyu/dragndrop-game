package com.aulia.belajariqra;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PilihActivity extends AppCompatActivity {

    @BindView(R.id.tv_iqra_level)
    TextView tvLevel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pilih);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.ll_belajar)
    void belajar() {
        startActivity(
                new Intent(this, BelajarActivity.class)
        );
    }
}
