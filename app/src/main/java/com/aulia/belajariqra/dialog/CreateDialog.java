package com.aulia.belajariqra.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.EditText;

import com.aulia.belajariqra.GameSave;
import com.aulia.belajariqra.R;
import com.orhanobut.hawk.Hawk;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by iamnubs on 4/9/17.
 */

public class CreateDialog extends Dialog {
    Context context;

    @BindView(R.id.age)
    EditText mAge;
    @BindView(R.id.name)
    EditText mName;

    public CreateDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_create);
        ButterKnife.bind(this);

        getWindow().setBackgroundDrawable(null);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        int width = (int) (displaymetrics.widthPixels * 0.9);
        getWindow().setLayout(width, WRAP_CONTENT);
    }

    @OnClick(R.id.action_create)
    void onCreateClick() {
        GameSave gameSave = new GameSave();
        gameSave.age = Integer.parseInt(mAge.getText().toString());

        HashMap<String, GameSave> mc = Hawk.get("Save", new HashMap<String, GameSave>());
        mc.put(mName.getText().toString(), gameSave);

        Hawk.put("Save", mc);

        dismiss();
    }
}
