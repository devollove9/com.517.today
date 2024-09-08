package com.us517.today.util;

import android.content.Context;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.us517.today.R;

public class Dialog {
    private static ProgressBar progressDialog;

    public synchronized static void showLoadingDialog(Context activity) {
        if (progressDialog == null && activity != null) {
            progressDialog = new ProgressBar(activity);
            //progressDialog.setMessage(activity.getString(R.string.loading));
            //progressDialog.setCancelable(false);
            //progressDialog.show();
        }
    }
    public static void showToast(Context context, String text) {
        if (context != null && text != null) {
            Toast.makeText(context, text, Toast.LENGTH_LONG).show();
        }
    }

    public static void showToast(Context context, int resId) {
        if (context != null) {
            Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
        }
    }
}
