package com.aulia.belajariqra;

import android.content.Context;

public class Huruf {
    public String text;

    public int image;

    public static Huruf get(Context context, int position) {
        int[] images = new int[]{
                R.drawable.ic_alif,
                R.drawable.ic_ba,
                R.drawable.ic_ta,
                R.drawable.ic_tsa,
                R.drawable.ic_jim,
                R.drawable.ic_ha,
                R.drawable.ic_kha,
                R.drawable.ic_dal,
                R.drawable.ic_dzal,
                R.drawable.ic_ra,
                R.drawable.ic_za,
                R.drawable.ic_sin,
                R.drawable.ic_syin,
                R.drawable.ic_shaad,
                R.drawable.ic_dhaad,
                R.drawable.ic_tho,
                R.drawable.ic_dhlo,
                R.drawable.ic_ain,
                R.drawable.ic_ghoin,
                R.drawable.ic_fa,
                R.drawable.ic_qaf,
                R.drawable.ic_kaf,
                R.drawable.ic_lam,
                R.drawable.ic_mim,
                R.drawable.ic_nun,
                R.drawable.ic_haa,
                R.drawable.ic_wawu,
                R.drawable.ic_lam_alif,
                R.drawable.ic_hamzah,
                R.drawable.ic_yaa,
        };

        Huruf huruf = new Huruf();
        huruf.image = images[position];
        huruf.text = context.getResources().getStringArray(R.array.string_huruf)[position];

        return huruf;
    }
}
