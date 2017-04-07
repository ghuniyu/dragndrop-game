package com.aulia.belajariqra;

import android.support.annotation.DrawableRes;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;

public class GerakUtils {
    public static void init(final RelativeLayout stage, final @DrawableRes int fdr, final @DrawableRes int sdr, final int count) {
        stage.post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < count; i++) {
                    final ImageView object = new ImageView(stage.getContext());
                    object.post(new Runnable() {
                        @Override
                        public void run() {
                            if (object.getTag() == null || object.getTag().equals(0)) {
                                object.setImageResource(fdr);
                                object.setTag(1);
                            } else {
                                object.setImageResource(sdr);
                                object.setTag(0);
                            }

                            object.postDelayed(this, random(200, 100));
                        }
                    });

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
        params.leftMargin = isLeft ? -1 * 100 : width + 100;
        params.topMargin = random(height - 200, 100);

        object.setLayoutParams(params);
        object.setScaleX(isLeft ? 1 : -1);

        TranslateAnimation ta = new TranslateAnimation(0, isLeft ? width + 200 : -1 * width - 200, 0, 0);
        ta.setDuration(random(3000, 3000));
        ta.setStartOffset(random(1000, 0));
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
