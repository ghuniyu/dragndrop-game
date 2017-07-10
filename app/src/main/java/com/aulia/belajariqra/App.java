package com.aulia.belajariqra;

import android.app.Application;

import com.orhanobut.hawk.Hawk;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Hawk.init(this).build();

        CalligraphyConfig.initDefault(
                new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/mr-sunshine-2.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
    }
}
