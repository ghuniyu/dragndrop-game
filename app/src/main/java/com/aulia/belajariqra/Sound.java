package com.aulia.belajariqra;

import android.content.Context;
import android.media.MediaPlayer;

public class Sound {
    public static void click(Context context) {
        MediaPlayer mp = MediaPlayer.create(context, R.raw.click);
        mp.start();
    }
}
