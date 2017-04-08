package com.aulia.belajariqra;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainMenuActivity extends AppCompatActivity {

    Animation rotateCW;
    Animation rotateCCW;
    Animation move;

    @BindView(R.id.circle_in)
    ImageView c1;
    @BindView(R.id.circle_in1)
    ImageView c2;
    @BindView(R.id.circle_in2)
    ImageView c3;

    @BindView(R.id.circle_out)
    ImageView co1;
    @BindView(R.id.circle_out1)
    ImageView co2;
    @BindView(R.id.circle_out2)
    ImageView co3;

    @BindView(R.id.stage)
    RelativeLayout stage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();

        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        setContentView(R.layout.activity_main_menu);

        ButterKnife.bind(this);

        rotateCW = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_cw);
        rotateCCW = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_ccw);

        move = AnimationUtils.loadAnimation(this, R.anim.move);
        move.setDuration(10000);
        move.setRepeatCount(-1);
        move.setRepeatMode(Animation.REVERSE);
        move.setInterpolator(new LinearInterpolator());

        c1.startAnimation(rotateCW);
        c2.startAnimation(rotateCW);
        c3.startAnimation(rotateCW);

        co1.startAnimation(rotateCCW);
        co2.startAnimation(rotateCCW);
        co3.startAnimation(rotateCCW);

        BirdMotion.init(stage, R.drawable.ic_bird_down, R.drawable.ic_bird_up, 10);
        CloudMotion.init(stage, R.drawable.ic_cloud_very_small, 10);
    }


    @OnClick(R.id.action_exit)
    void exit() {
        finish();
    }

    @OnClick(R.id.ll_menu)
    void openPilih() {
        startActivity(new Intent(this, MenuActivity.class));
    }
}
