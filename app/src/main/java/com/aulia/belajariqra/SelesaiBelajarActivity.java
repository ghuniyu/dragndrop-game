package com.aulia.belajariqra;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelesaiBelajarActivity extends BaseActivity {
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
