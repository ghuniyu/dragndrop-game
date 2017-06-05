package com.aulia.belajariqra;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Pilih3Activity extends AppCompatActivity {
    @BindView(R.id.stage)
    RelativeLayout stage;

    @BindView(R.id.circle_out1)
    ImageView c1;

    @BindView(R.id.circle_out2)
    ImageView c2;


    Animation rotate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pilih_3);

        ButterKnife.bind(this);
        BirdMotion.init(stage, R.drawable.ic_bird_down, R.drawable.ic_bird_up, 7);
        CloudMotion.init(stage, R.drawable.ic_cloud_very_small, 5);
        CloudMotion.init(stage, R.drawable.ic_cloud_small, 4);
        CloudMotion.init(stage, R.drawable.ic_cloud_medium, 3);
        CloudMotion.init(stage, R.drawable.ic_cloud_big, 2);

        rotate = AnimationUtils.loadAnimation(this, R.anim.rotate_ccw);

        c1.setAnimation(rotate);
        c2.setAnimation(rotate);

    }

    @OnClick(R.id.tvBack)
    void back() {
        finish();
    }

    @OnClick(R.id.ll_belajar)
    void openBelajar() {
        Sound.click(this);

        startActivity(new Intent(this, Belajar3Activity.class));
    }

    @OnClick(R.id.action_open_main_menu)
    void openMainMenu() {
        Intent intent = new Intent(this, MainMenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
    }

    @OnClick(R.id.ll_bermain)
    void openMenu() {
        Sound.click(this);

        startActivity(new Intent(this, Game3Activity.class));
    }
}
