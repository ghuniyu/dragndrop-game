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

public class Game3Activity extends BaseActivity {
    @BindView(R.id.a)
    ImageView mA;
    @BindView(R.id.b)
    ImageView mB;
    @BindView(R.id.c)
    ImageView mC;
    @BindView(R.id.d)
    ImageView mD;
    @BindView(R.id.option_1)
    ImageView mOption1;
    @BindView(R.id.option_2)
    ImageView mOption2;
    @BindView(R.id.option_3)
    ImageView mOption3;

    @BindView(R.id.answer)
    ImageView mAnswer;
    @BindView(R.id.answer_option_below)
    ImageView mAnswerOptionBelow;
    @BindView(R.id.answer_option_top)
    ImageView mAnswerOptionTop;
    @BindView(R.id.question)
    TextView mQuestion;

    @BindView(R.id.stage)
    RelativeLayout mStage;
    @BindView(R.id.score)
    TextView mScore;
    @BindView(R.id.root)
    View mRoot;

    @BindView(R.id.status)
    ImageView mStatus;

    private final Object[][] mQuestions = new Object[][]{
            {"Ba", 1, "A"},
            {"Du", 7, "U"},
            {"Di", 19, "I"},
            {"Nu", 24, "U"},
            {"Ri", 9, "I"}
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

        setContentView(R.layout.activity_game_3);

        ButterKnife.bind(this);

        Tutorial.show(this, 2);

        BirdMotion.init(mStage, R.drawable.ic_bird_down, R.drawable.ic_bird_up, 4);

        CloudMotion.init(mStage, R.drawable.ic_cloud_very_small, 8);

        shuffleQuestions();

        load();
    }

    @OnTouch(value = {R.id.a, R.id.b, R.id.c, R.id.d})
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

                if (isTargetContains(mAnswer, mCurrentLayoutParams.leftMargin + v.getWidth() / 2, mCurrentLayoutParams.topMargin + v.getHeight() / 2)) {
                    mAnswer.setImageResource(Huruf.get(this, (int) v.getTag()).image);
                    mAnswer.setTag(v.getTag());

                    check();
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

    @OnTouch(value = {R.id.option_1, R.id.option_2, R.id.option_3})
    boolean dragOption(View v, MotionEvent event) {
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

                if (isTargetContains(mAnswerOptionBelow, mCurrentLayoutParams.leftMargin + v.getWidth() / 2, mCurrentLayoutParams.topMargin + v.getHeight() / 2)) {
                    mAnswerOptionBelow.setImageDrawable(((ImageView) v).getDrawable());
                    mAnswerOptionBelow.setTag(v.getTag());

                    check();
                }

                if (isTargetContains(mAnswerOptionTop, mCurrentLayoutParams.leftMargin + v.getWidth() / 2, mCurrentLayoutParams.topMargin + v.getHeight() / 2)) {
                    mAnswerOptionTop.setImageDrawable(((ImageView) v).getDrawable());
                    mAnswerOptionTop.setTag(v.getTag());

                    check();
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

    private void check() {
        if (mAnswer.getTag() != null && mAnswerOptionBelow.getTag() != null) {
            if (mAnswer.getTag().equals(mQuestions[mCurrentQuestion][1]) && mAnswerOptionBelow.getTag().equals(mQuestions[mCurrentQuestion][2])) {
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

        if (mAnswer.getTag() != null && mAnswerOptionTop.getTag() != null) {
            if (mAnswer.getTag().equals(mQuestions[mCurrentQuestion][1]) && mAnswerOptionTop.getTag().equals(mQuestions[mCurrentQuestion][2])) {
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
    }

    private void load() {
        mQuestion.setText(mQuestions[mCurrentQuestion][0].toString());

        List<Integer> options = new ArrayList<>();

        for (int i = 0; i < 29; i++) {
            if (!mQuestions[mCurrentQuestion][1].equals(i)) {
                options.add(i);
            }
        }

        Collections.shuffle(options);

        options = options.subList(0, 3);
        options.add((int) mQuestions[mCurrentQuestion][1]);

        Collections.shuffle(options);

        mA.setImageResource(Huruf.get(this, options.get(0)).image);
        mA.setTag(options.get(0));
        mB.setImageResource(Huruf.get(this, options.get(1)).image);
        mB.setTag(options.get(1));
        mC.setImageResource(Huruf.get(this, options.get(2)).image);
        mC.setTag(options.get(2));
        mD.setImageResource(Huruf.get(this, options.get(3)).image);
        mD.setTag(options.get(3));

        mAnswer.setImageDrawable(null);
        mAnswer.setTag(null);
        mAnswerOptionBelow.setImageDrawable(null);
        mAnswerOptionBelow.setTag(null);
        mAnswerOptionTop.setImageDrawable(null);
        mAnswerOptionTop.setTag(null);
    }

    private void shuffleQuestions() {
        Random random = new Random();

        for (int i = 0; i < mQuestions.length; i++) {
            int r = random.nextInt(mQuestions.length);

            Object[] t = mQuestions[i];

            mQuestions[i] = mQuestions[r];
            mQuestions[r] = t;
        }
    }
}
