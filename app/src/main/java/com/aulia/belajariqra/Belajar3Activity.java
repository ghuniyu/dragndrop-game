package com.aulia.belajariqra;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Belajar3Activity extends AppCompatActivity {
    private final int[] mImages = {
            R.drawable.pertama_m,
            R.drawable.kedua_m,
            R.drawable.ketiga_m,
            R.drawable.keempat_m,
            R.drawable.kelima_m,
            R.drawable.keenam_m,
            R.drawable.ketujuh_m,
            R.drawable.kedelapan_m,
            R.drawable.kesembilan_m,
            R.drawable.kesepuluh_m
    };

    private int mCurrentPage;

    @BindView(R.id.action_next)
    ImageView mActionNext;
    @BindView(R.id.action_previous)
    ImageView mActionPrevious;
    @BindView(R.id.image)
    ImageView mImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_belajar_3);

        ButterKnife.bind(this);

        load();
    }


    @OnClick(R.id.action_next)
    void next() {
        mCurrentPage += 1;

        load();
    }

    @OnClick(R.id.action_previous)
    void previous() {
        mCurrentPage -= 1;

        load();
    }

    private void load() {
        if (mCurrentPage == 0) {
            mActionPrevious.setVisibility(View.INVISIBLE);
        } else if (mCurrentPage == mImages.length) {
            startActivity(new Intent(this, SelesaiBelajarActivity.class));
            finish();

            return;
        } else {
            mActionPrevious.setVisibility(View.VISIBLE);
        }

        mImage.setImageResource(mImages[mCurrentPage]);
    }
}
