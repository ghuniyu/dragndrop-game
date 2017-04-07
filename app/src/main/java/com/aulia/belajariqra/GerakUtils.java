package com.aulia.belajariqra;

import android.support.annotation.DrawableRes;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;

public class GerakUtils {
    public static void init(final RelativeLayout stage, final @DrawableRes int drawableRes, final int count) {
        stage.post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < count; i++) {
                    ImageView object = new ImageView(stage.getContext());
                    object.setImageResource(drawableRes);

                    stage.addView(object);

                    move(object, stage.getHeight(), stage.getWidth());
                }
            }
        });
    }

    private static void move(final ImageView object, final int height, final int width) {
        Random random = new Random();

        boolean isLeft = random.nextInt(2) == 0;

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) object.getLayoutParams();
        params.leftMargin = isLeft ? -1 * 100 : width + 100;
        params.topMargin = random.nextInt(height - 200) + 100;

        object.setLayoutParams(params);
        object.setScaleX(isLeft ? 1 : -1);

        TranslateAnimation ta = new TranslateAnimation(0, isLeft ? width + 200 : -1 * width - 200, 0, 0);
        ta.setDuration(random.nextInt(3000) + 3000);
        ta.setStartOffset(random.nextInt(1000));
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
