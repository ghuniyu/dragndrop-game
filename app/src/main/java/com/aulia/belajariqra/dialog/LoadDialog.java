package com.aulia.belajariqra.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Window;

import com.aulia.belajariqra.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by iamnubs on 4/9/17.
 */

public class LoadDialog extends Dialog {
    Context context;

    public LoadDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_load);
        ButterKnife.bind(this);

        getWindow().setBackgroundDrawable(null);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        int width = (int) (displaymetrics.widthPixels * 0.9);
        getWindow().setLayout(width, WRAP_CONTENT);

        //Pakai adapter untuk munculin semua usernya bang
    }

    @OnClick(R.id.create)
    void dialogCreate() {
        final CreateDialog dialog = new CreateDialog(context);
        dialog.show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {

            }
        });
    }
}
