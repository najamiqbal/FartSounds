package com.funnyringtone.bestringtones.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.funnyringtone.bestringtones.R;


public class ExitDialogUtils {

    public static Dialog createExitDialog(final Activity context, final DialogPageOnClickListener listener) {
        LayoutInflater inflater = LayoutInflater.from(context);
        final View rootView = inflater.inflate(R.layout.app_exit_dialog, null);
        final Dialog myDialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        Window window = myDialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = WindowManager.LayoutParams.MATCH_PARENT;
        myDialog.setCancelable(true);
        myDialog.setContentView(rootView);
        AdmobNativeClass.refreshAd(context,
                (FrameLayout) rootView.findViewById(R.id.framlayoutAds));

        final View cancelButton = rootView.findViewById(R.id.btnCancle);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
                if (listener != null) {
                    listener.onNOButtonOnClick();
                }
            }
        });

        final View okButton = rootView.findViewById(R.id.btnOk);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
                if (listener != null) {
                    listener.onOKButtonOnClick();
                }
            }
        });
        return myDialog;
    }

    public interface DialogPageOnClickListener {
        void onOKButtonOnClick();

        void onNOButtonOnClick();
    }


}
