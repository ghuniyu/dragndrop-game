package com.aulia.belajariqra;

import android.app.Application;

import com.orhanobut.hawk.Hawk;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Hawk.init(this).build();
    }
}
