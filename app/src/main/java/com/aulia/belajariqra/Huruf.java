package com.aulia.belajariqra;

import android.content.Context;

public class Huruf {
    public String text;

    public int image;

    public static Huruf get(Context context, int position) {
        int[] images = new int[]{
                R.drawable.alif,
                R.drawable.ba,
                R.drawable.ta,
                R.drawable.tsa,
                R.drawable.jim,
                R.drawable.ha,
                R.drawable.kho,
                R.drawable.dal,
                R.drawable.dzal,
                R.drawable.ra,
                R.drawable.zai,
                R.drawable.sin,
                R.drawable.syin,
                R.drawable.shood,
                R.drawable.dhood,
                R.drawable.tho,
                R.drawable.dzo,
                R.drawable.ain,
                R.drawable.ghoin,
                R.drawable.fa,
                R.drawable.qof,
                R.drawable.kaf,
                R.drawable.laam,
                R.drawable.mim,
                R.drawable.nun,
                R.drawable.haa,
                R.drawable.wauw,
                R.drawable.hamzah,
                R.drawable.ya
        };

        Huruf huruf = new Huruf();
        huruf.image = images[position];
        huruf.text = context.getResources().getStringArray(R.array.string_huruf)[position];

        return huruf;
    }
}
