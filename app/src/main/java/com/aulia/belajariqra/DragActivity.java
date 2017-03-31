package com.aulia.belajariqra;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class DragActivity extends AppCompatActivity {
    private RelativeLayout.LayoutParams mCurrentLayoutParams;
    private RelativeLayout.LayoutParams mInitialLayoutParams;
    private View mRoot;
    private View mTarget;

    private int mX, mY;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drag);

        mRoot = findViewById(R.id.root);
        mTarget = findViewById(R.id.target);

        bindEvent(R.id.a);
        bindEvent(R.id.b);
        bindEvent(R.id.c);
    }

    private boolean isTargetContains(int oX, int oY) {
        int[] l = new int[2];

        mTarget.getLocationInWindow(l);

        int x = l[0];
        int y = l[1];

        mRoot.getLocationInWindow(l);

        x -= l[0];
        y -= l[1];

        int h = mTarget.getHeight();
        int w = mTarget.getWidth();

        return !(oX < x || oX > x + w || oY < y || oY > y + h);
    }

    private void bindEvent(@IdRes int id) {
        findViewById(id).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mX = (int) event.getRawX();
                        mY = (int) event.getRawY();

                        mCurrentLayoutParams = new RelativeLayout.LayoutParams((RelativeLayout.LayoutParams) v.getLayoutParams());
                        mInitialLayoutParams = new RelativeLayout.LayoutParams((RelativeLayout.LayoutParams) v.getLayoutParams());

                        return true;

                    case MotionEvent.ACTION_UP:
                        v.setLayoutParams(mInitialLayoutParams);

                        if (isTargetContains(mCurrentLayoutParams.leftMargin + v.getWidth() / 2, mCurrentLayoutParams.topMargin + v.getHeight() / 2)) {
                            Toast.makeText(DragActivity.this, v.getTag().toString(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(DragActivity.this, "Jangan Gemeteran Dong", Toast.LENGTH_SHORT).show();
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
        });
    }
}
