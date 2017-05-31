package com.aulia.belajariqra;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Belajar1Activity extends AppCompatActivity {
    @BindView(R.id.h1i)
    ImageView mH1i;
    @BindView(R.id.h2i)
    ImageView mH2i;
    @BindView(R.id.h1t)
    TextView mH1t;
    @BindView(R.id.h2t)
    TextView mH2t;
    @BindView(R.id.action_next)
    ImageView mNextAction;
    @BindView(R.id.action_previous)
    ImageView mPreviousAction;

    @BindView(R.id.stage)
    RelativeLayout stage;

    private int mCurrentPage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_belajar_1);

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

        load();
    }

    private void load() {
        if (mCurrentPage == 1) {
            mPreviousAction.setVisibility(View.INVISIBLE);
        } else if (mCurrentPage == 16) {
            startActivity(new Intent(this, SelesaiBelajarActivity.class));
            finish();

            return;
        } else {
            mPreviousAction.setVisibility(View.VISIBLE);
        }

        int firstPosition = 2 * mCurrentPage - 2;
        int secondPosition = 2 * mCurrentPage - 1;

        Huruf h1 = Huruf.get(this, firstPosition);
        Huruf h2;

        if (secondPosition == 29) {
            h2 = new Huruf();
        } else {
            h2 = Huruf.get(this, secondPosition);
        }

        HashMap<String, GameSave> mc = Hawk.get("Save", new HashMap<String, GameSave>());

        GameSave gameSave = mc.get(Hawk.<String>get("currentUser"));
        gameSave.learningProgress1.add(firstPosition);

        if (secondPosition != 29) {
            gameSave.learningProgress1.add(secondPosition);
        }

        Hawk.put("Save", mc);

        mH1i.setImageResource(h1.image);
        mH1t.setText(h1.text);
        mH2i.setImageResource(h2.image);
        mH2t.setText(h2.text);
    }
}
