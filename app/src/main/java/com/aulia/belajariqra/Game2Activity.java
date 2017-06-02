package com.aulia.belajariqra;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTouch;

public class Game2Activity extends AppCompatActivity {
    @BindView(R.id.a)
    ImageView mA;
    @BindView(R.id.b)
    ImageView mB;
    @BindView(R.id.c)
    ImageView mC;
    @BindView(R.id.d)
    ImageView mD;
    @BindView(R.id.e)
    ImageView mE;

    @BindView(R.id.answer1)
    ImageView mAnswer1;
    @BindView(R.id.answer2)
    ImageView mAnswer2;
    @BindView(R.id.question)
    ImageView mQuestion;

    @BindView(R.id.root)
    View mRoot;

    @BindView(R.id.status)
    ImageView mStatus;

    private final int[][] mQuestions = new int[][]{
            {R.drawable.i_2_5, 6, 12}
    };

    private RelativeLayout.LayoutParams mCurrentLayoutParams;
    private RelativeLayout.LayoutParams mInitialLayoutParams;

    private int mCurrentQuestion;
    private int mX;
    private int mY;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game_2);

        ButterKnife.bind(this);

        load();
    }

    @OnTouch(value = {R.id.a, R.id.b, R.id.c, R.id.d, R.id.e})
    boolean drag(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mX = (int) event.getRawX();
                mY = (int) event.getRawY();

                mCurrentLayoutParams = new RelativeLayout.LayoutParams((RelativeLayout.LayoutParams) v.getLayoutParams());
                mInitialLayoutParams = new RelativeLayout.LayoutParams((RelativeLayout.LayoutParams) v.getLayoutParams());

                return true;

            case MotionEvent.ACTION_UP:
                v.setLayoutParams(mInitialLayoutParams);

                if (isTargetContains(mAnswer1, mCurrentLayoutParams.leftMargin + v.getWidth() / 2, mCurrentLayoutParams.topMargin + v.getHeight() / 2)) {
                    mAnswer1.setImageResource((int) v.getTag());
                    mAnswer1.setTag(v.getTag());
                }

                if (isTargetContains(mAnswer2, mCurrentLayoutParams.leftMargin + v.getWidth() / 2, mCurrentLayoutParams.topMargin + v.getHeight() / 2)) {
                    mAnswer2.setImageResource((int) v.getTag());
                    mAnswer2.setTag(v.getTag());
                }

                if (mAnswer1.getTag() != null && mAnswer2.getTag() != null) {
                    Huruf h1 = Huruf.get(this, mQuestions[mCurrentQuestion][1]);
                    Huruf h2 = Huruf.get(this, mQuestions[mCurrentQuestion][2]);

                    if (mAnswer1.getTag().equals(h1.image) && mAnswer2.getTag().equals(h2.image)) {
                        mStatus.setImageResource(R.drawable.hebat);
                    } else {
                        mStatus.setImageResource(R.drawable.yah_salah);
                    }

                    mStatus.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mStatus.setImageDrawable(null);
                        }
                    }, 1000);
                }

                return true;

            case MotionEvent.ACTION_MOVE:
                mCurrentLayoutParams.leftMargin = (int) event.getRawX() - mX + mInitialLayoutParams.leftMargin;
                mCurrentLayoutParams.topMargin = (int) event.getRawY() - mY + mInitialLayoutParams.topMargin;

                v.setLayoutParams(mCurrentLayoutParams);

                return true;

            default:
                return false;
        }
    }

    private boolean isTargetContains(ImageView target, int oX, int oY) {
        int[] l = new int[2];

        target.getLocationInWindow(l);

        int x = l[0];
        int y = l[1];

        mRoot.getLocationInWindow(l);

        x -= l[0];
        y -= l[1];

        int h = target.getHeight();
        int w = target.getWidth();

        return !(oX < x || oX > x + w || oY < y || oY > y + h);
    }

    private void load() {
        mQuestion.setImageResource(mQuestions[mCurrentQuestion][0]);

        List<Integer> options = new ArrayList<>();

        for (int i = 0; i < 29; i++) {
            if (i != mQuestions[mCurrentQuestion][1] && i != mQuestions[mCurrentQuestion][2]) {
                options.add(i);
            }
        }

        Collections.shuffle(options);

        options = options.subList(0, 3);
        options.add(mQuestions[mCurrentQuestion][1]);
        options.add(mQuestions[mCurrentQuestion][2]);

        Collections.shuffle(options);

        mA.setImageResource(Huruf.get(this, options.get(0)).image);
        mA.setTag(Huruf.get(this, options.get(0)).image);
        mB.setImageResource(Huruf.get(this, options.get(1)).image);
        mB.setTag(Huruf.get(this, options.get(1)).image);
        mC.setImageResource(Huruf.get(this, options.get(2)).image);
        mC.setTag(Huruf.get(this, options.get(2)).image);
        mD.setImageResource(Huruf.get(this, options.get(3)).image);
        mD.setTag(Huruf.get(this, options.get(3)).image);
        mE.setImageResource(Huruf.get(this, options.get(4)).image);
        mE.setTag(Huruf.get(this, options.get(4)).image);
    }
}
