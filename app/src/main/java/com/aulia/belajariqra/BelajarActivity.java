package com.aulia.belajariqra;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BelajarActivity extends AppCompatActivity {
    @BindView(R.id.h1i)
    ImageView mH1i;
    @BindView(R.id.h2i)
    ImageView mH2i;
    @BindView(R.id.h1t)
    TextView mH1t;
    @BindView(R.id.h2t)
    TextView mH2t;

    @BindView(R.id.stage)
    RelativeLayout stage;

    private int mCurrentPage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_belajar);

        ButterKnife.bind(this);

        BirdMotion.init(stage, R.drawable.ic_bird_down, R.drawable.ic_bird_up, 7);
        CloudMotion.init(stage, R.drawable.ic_cloud_very_small, 5);
        CloudMotion.init(stage, R.drawable.ic_cloud_small, 4);
        CloudMotion.init(stage, R.drawable.ic_cloud_medium, 3);
        CloudMotion.init(stage, R.drawable.ic_cloud_big, 2);
        next();
    }

    @OnClick(R.id.tvBack)
    void back() {
        finish();
    }

    @OnClick(R.id.action_next)
    void next() {
        mCurrentPage += 1;

        if (mCurrentPage > 15) {
            mCurrentPage = 1;
        }

        load();
    }

    @OnClick(R.id.action_open_main_menu)
    void openMainMenu() {
        Intent intent = new Intent(this, MainMenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
    }

    @OnClick(R.id.action_previous)
    void previous() {
        mCurrentPage -= 1;

        if (mCurrentPage < 1) {
            mCurrentPage = 15;
        }

        load();
    }

    private void load() {
        Huruf h1 = Huruf.get(this, 2 * mCurrentPage - 2);
        Huruf h2 = Huruf.get(this, 2 * mCurrentPage - 1);

        HashMap<String, GameSave> mc = Hawk.get("Save", new HashMap<String, GameSave>());

        GameSave gameSave = mc.get("current");
        gameSave.learningProgress.add(2 * mCurrentPage - 2);
        gameSave.learningProgress.add(2 * mCurrentPage - 1);

        Hawk.put("Save", mc);

        mH1i.setImageResource(h1.image);
        mH1t.setText(h1.text);
        mH2i.setImageResource(h2.image);
        mH2t.setText(h2.text);
    }
}
