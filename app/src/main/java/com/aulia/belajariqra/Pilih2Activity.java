package com.aulia.belajariqra;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Pilih2Activity extends BaseActivity {
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

        setContentView(R.layout.activity_pilih_1);

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

        startActivity(new Intent(this, Belajar2Activity.class));
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

        HashMap<String, GameSave> mc = Hawk.get("Save", new HashMap<String, GameSave>());

        GameSave gameSave = mc.get(Hawk.<String>get("currentUser"));

        if (gameSave.learningProgress2.size() == 12) {
            startActivity(new Intent(this, Game2Activity.class));
        } else {
            Toast.makeText(this, "Adek Harus Belajar Dulu", Toast.LENGTH_SHORT).show();
        }
    }
}
