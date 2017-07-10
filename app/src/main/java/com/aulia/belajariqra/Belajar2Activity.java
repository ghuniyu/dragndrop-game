package com.aulia.belajariqra;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.orhanobut.hawk.Hawk;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Belajar2Activity extends BaseActivity {
    private final int[] mImages = {
            R.drawable.pertama,
            R.drawable.kedua,
            R.drawable.ketiga,
            R.drawable.keempat,
            R.drawable.kelima,
            R.drawable.keenam,
            R.drawable.ketujuh,
            R.drawable.kedelapan,
            R.drawable.kesembilan,
            R.drawable.kesepuluh,
            R.drawable.kesebelas,
            R.drawable.keduabelas
    };

    private int mCurrentPage;

    @BindView(R.id.action_next)
    ImageView mActionNext;
    @BindView(R.id.action_previous)
    ImageView mActionPrevious;
    @BindView(R.id.image)
    ImageView mImage;
    @BindView(R.id.stage)
    RelativeLayout stage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_belajar_2);

        ButterKnife.bind(this);

        BirdMotion.init(stage, R.drawable.ic_bird_down, R.drawable.ic_bird_up, 7);
        CloudMotion.init(stage, R.drawable.ic_cloud_very_small, 5);
        CloudMotion.init(stage, R.drawable.ic_cloud_small, 4);
        CloudMotion.init(stage, R.drawable.ic_cloud_medium, 3);
        CloudMotion.init(stage, R.drawable.ic_cloud_big, 2);

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

        HashMap<String, GameSave> mc = Hawk.get("Save", new HashMap<String, GameSave>());

        GameSave gameSave = mc.get(Hawk.<String>get("currentUser"));
        gameSave.learningProgress2.add(mCurrentPage);

        Hawk.put("Save", mc);
    }
}
