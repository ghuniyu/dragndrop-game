package com.aulia.belajariqra;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTouch;

public class Game3Activity extends AppCompatActivity {
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
    @BindView(R.id.answer_option)
    ImageView mAnswerOption;
    @BindView(R.id.question)
    TextView mQuestion;

    @BindView(R.id.root)
    View mRoot;

    @BindView(R.id.status)
    ImageView mStatus;

    private final Object[][] mQuestions = new Object[][]{
            {"Bu", 1, "U"}
    };

    private RelativeLayout.LayoutParams mCurrentLayoutParams;
    private RelativeLayout.LayoutParams mInitialLayoutParams;

    private int mCurrentQuestion;
    private int mX;
    private int mY;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game_3);

        ButterKnife.bind(this);

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

                if (isTargetContains(mAnswerOption, mCurrentLayoutParams.leftMargin + v.getWidth() / 2, mCurrentLayoutParams.topMargin + v.getHeight() / 2)) {
                    mAnswerOption.setImageDrawable(((ImageView) v).getDrawable());
                    mAnswerOption.setTag(v.getTag());

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
        if (mAnswer.getTag() != null && mAnswerOption.getTag() != null) {
            if (mAnswer.getTag().equals(mQuestions[mCurrentQuestion][1]) && mAnswerOption.getTag().equals(mQuestions[mCurrentQuestion][2])) {
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
    }
}
