package com.aulia.belajariqra;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTouch;

public class Game2Activity extends BaseActivity {
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

    @BindView(R.id.stage)
    RelativeLayout mStage;
    @BindView(R.id.score)
    TextView mScore;
    @BindView(R.id.root)
    View mRoot;

    @BindView(R.id.reference)
    ImageView mReference;
    @BindView(R.id.status)
    ImageView mStatus;

    private final int[][] mQuestions = new int[][]{
            {R.drawable.i_2_5, 6, 12},
            {R.drawable.i_2_5, 6, 12},
            {R.drawable.i_2_5, 6, 12},
            {R.drawable.i_2_5, 6, 12},
            {R.drawable.i_2_5, 6, 12}
    };

    private RelativeLayout.LayoutParams mCurrentLayoutParams;
    private RelativeLayout.LayoutParams mInitialLayoutParams;

    private int mCurrentQuestion;
    private int mCurrentScore;
    private int mX;
    private int mY;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game_2);

        ButterKnife.bind(this);

        Tutorial.show(this, 1);

        BirdMotion.init(mStage, R.drawable.ic_bird_down, R.drawable.ic_bird_up, 4);

        CloudMotion.init(mStage, R.drawable.ic_cloud_very_small, 8);

        shuffleQuestions();

        load();

        mReference.post(new Runnable() {
            @Override
            public void run() {
                double ratio = mReference.getWidth() / 945;

                RelativeLayout.LayoutParams params;

                params = (RelativeLayout.LayoutParams) mA.getLayoutParams();
                params.leftMargin = (int) (453 * ratio - mA.getWidth() / 4);
                params.topMargin = (int) (168 * ratio - mA.getHeight() / 4);

                mA.setLayoutParams(params);

                params = (RelativeLayout.LayoutParams) mB.getLayoutParams();
                params.leftMargin = (int) (334 * ratio - mB.getWidth() / 4);
                params.topMargin = (int) (223 * ratio - mB.getHeight() / 4);

                mB.setLayoutParams(params);

                params = (RelativeLayout.LayoutParams) mC.getLayoutParams();
                params.leftMargin = (int) (599 * ratio - mC.getWidth() / 4);
                params.topMargin = (int) (237 * ratio - mC.getHeight() / 4);

                mC.setLayoutParams(params);

                params = (RelativeLayout.LayoutParams) mD.getLayoutParams();
                params.leftMargin = (int) (386 * ratio - mD.getWidth() / 4);
                params.topMargin = (int) (378 * ratio - mD.getHeight() / 4);

                mD.setLayoutParams(params);

                params = (RelativeLayout.LayoutParams) mE.getLayoutParams();
                params.leftMargin = (int) (555 * ratio - mE.getWidth() / 4);
                params.topMargin = (int) (376 * ratio - mE.getHeight() / 4);

                mE.setLayoutParams(params);
            }
        });
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

                if (mCurrentQuestion == mQuestions.length) {
                    return true;
                }

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

                        mCurrentScore += 20;

                        mScore.setText(String.valueOf(mCurrentScore));
                    } else {
                        mStatus.setImageResource(R.drawable.yah_salah);
                    }

                    mStatus.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mStatus.setImageDrawable(null);
                        }
                    }, 1000);

                    mCurrentQuestion++;

                    if (mCurrentQuestion < mQuestions.length) {
                        load();
                    } else {
                        Toast.makeText(this, "Selesai", Toast.LENGTH_LONG).show();
                    }
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

        mAnswer1.setImageDrawable(null);
        mAnswer1.setTag(null);
        mAnswer2.setImageDrawable(null);
        mAnswer2.setTag(null);
    }

    private void shuffleQuestions() {
        Random random = new Random();

        for (int i = 0; i < mQuestions.length; i++) {
            int r = random.nextInt(mQuestions.length);

            int[] t = mQuestions[i];

            mQuestions[i] = mQuestions[r];
            mQuestions[r] = t;
        }
    }
}
