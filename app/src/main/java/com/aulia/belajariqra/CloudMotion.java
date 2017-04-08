package com.aulia.belajariqra;

import android.support.annotation.DrawableRes;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;

public class CloudMotion {
    public static void init(final RelativeLayout stage, final @DrawableRes int dr, final int count) {
        stage.post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < count; i++) {
                    ImageView object = new ImageView(stage.getContext());
                    object.setImageResource(dr);

                    stage.addView(object);

                    move(object, stage.getHeight(), stage.getWidth());
                }
            }
        });
    }

    private static int random(int limit, int offset) {
        return new Random().nextInt(limit) + offset;
    }

    private static void move(final ImageView object, final int height, final int width) {
        boolean isLeft = random(2, 0) == 0;

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) object.getLayoutParams();
        params.leftMargin = isLeft ? -1 * 200 : width + 200;
        params.topMargin = random(height / 4, 0);

        object.setLayoutParams(params);
        object.setScaleX(isLeft ? 1 : -1);

        TranslateAnimation ta = new TranslateAnimation(0, isLeft ? width + 400 : -1 * width - 400, 0, 0);
        ta.setDuration(random(30000, 30000));
        ta.setStartOffset(random(10000, 0));
        ta.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                move(object, height, width);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        object.startAnimation(ta);
    }
}
