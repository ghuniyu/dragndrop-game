package com.aulia.belajariqra;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Tutorial {
    public static void show(AppCompatActivity activity, int i) {
        int[] images = {
                R.drawable.tut_1,
                R.drawable.tut_2,
                R.drawable.tut_3
        };

        final ImageView imageView = new ImageView(activity);
        imageView.setImageResource(images[i]);

        final ViewGroup container = (ViewGroup) activity.findViewById(android.R.id.content);
        container.addView(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.removeView(imageView);
            }
        });
    }
}
