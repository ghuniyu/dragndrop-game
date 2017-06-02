package com.aulia.belajariqra;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuActivity extends AppCompatActivity {
    @BindView(R.id.ll_iqra1)
    FrameLayout l1;
    @BindView(R.id.ll_iqra2)
    FrameLayout l2;
    @BindView(R.id.ll_iqra3)
    FrameLayout l3;
    @BindView(R.id.ll_iqra4)
    FrameLayout l4;
    @BindView(R.id.ll_iqra5)
    FrameLayout l5;
    @BindView(R.id.ll_iqra6)
    FrameLayout l6;

    @BindView(R.id.stage)
    RelativeLayout stage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);

        ButterKnife.bind(this);

        animate(l1);
        animate(l2);
        animate(l3);
        animate(l4);
        animate(l5);
        animate(l6);

        BirdMotion.init(stage, R.drawable.ic_bird_down, R.drawable.ic_bird_up, 7);
        CloudMotion.init(stage, R.drawable.ic_cloud_very_small, 5);
        CloudMotion.init(stage, R.drawable.ic_cloud_small, 4);
        CloudMotion.init(stage, R.drawable.ic_cloud_medium, 3);
        CloudMotion.init(stage, R.drawable.ic_cloud_big, 2);
    }

    void animate(FrameLayout layout) {
        Random r = new Random();
        int ran = r.nextInt(2) + 2;

        Animation mAnimation;
        mAnimation = new TranslateAnimation(
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0.1f);
        mAnimation.setDuration(ran * 1000);
        mAnimation.setRepeatCount(-1);
        mAnimation.setRepeatMode(Animation.REVERSE);
        mAnimation.setInterpolator(new LinearInterpolator());
        layout.startAnimation(mAnimation);
    }

    @OnClick(R.id.tvBack)
    void back() {
        finish();
    }

    @OnClick(R.id.ll_iqra1)
    void openGame1() {
        startActivity(new Intent(this, Pilih1Activity.class));
    }

    @OnClick(R.id.ll_iqra2)
    void openGame2() {
        startActivity(new Intent(this, Pilih2Activity.class));
    }

    @OnClick(R.id.action_open_main_menu)
    void openMainMenu() {
        Intent intent = new Intent(this, MainMenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
    }
}
