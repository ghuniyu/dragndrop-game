package com.aulia.belajariqra.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.Toast;

import com.aulia.belajariqra.GameSave;
import com.aulia.belajariqra.R;
import com.orhanobut.hawk.Hawk;

import java.util.HashMap;
import java.util.Map;

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
        HashMap<String, GameSave> mc = Hawk.get("Save", new HashMap<String, GameSave>());

        for (Map.Entry<String, GameSave> entry : mc.entrySet()) {
            Toast.makeText(context, entry.getKey(), Toast.LENGTH_SHORT).show();
        }
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
