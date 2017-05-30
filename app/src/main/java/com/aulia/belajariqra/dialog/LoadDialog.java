package com.aulia.belajariqra.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.aulia.belajariqra.GameSave;
import com.aulia.belajariqra.PilihActivity;
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

public class LoadDialog extends Dialog {
    Context context;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

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

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(new Adapter());

        getWindow().setBackgroundDrawable(null);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        int width = (int) (displaymetrics.widthPixels * 0.9);
        getWindow().setLayout(width, WRAP_CONTENT);
    }

    @OnClick(R.id.create)
    void dialogCreate() {
        final CreateDialog dialog = new CreateDialog(context);
        dialog.show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                mRecyclerView.setAdapter(new Adapter());
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView mName;

        private ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    private class Adapter extends RecyclerView.Adapter<ViewHolder> {
        private HashMap<String, GameSave> mGameSaves;

        private Adapter() {
            mGameSaves = Hawk.get("Save", new HashMap<String, GameSave>());
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false));
        }

        @Override
        public int getItemCount() {
            return mGameSaves.size();
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final String name = mGameSaves.keySet().toArray()[position].toString();

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Hawk.put("currentUser", name);

                    getContext().startActivity(new Intent(getContext(), PilihActivity.class));
                }
            });
            holder.mName.setText(mGameSaves.keySet().toArray()[position].toString());
        }
    }
}
