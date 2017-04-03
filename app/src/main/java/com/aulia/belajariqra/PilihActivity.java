package com.aulia.belajariqra;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

        setContentView(R.layout.activity_pilih);

        ButterKnife.bind(this);

        if (getIntent().hasExtra("level")){
            tvLevel.setText(getIntent().getIntExtra("level", 0));
        }
    }

    @OnClick(R.id.ll_belajar)
    void belajar(){
        new Intent(this, BelajarActivity.class);
    }
}
