package com.javiermarin.javininja.dialog;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.javiermarin.javininja.MainActivity;
import com.javiermarin.javininja.R;
import com.javiermarin.javininja.game.GameView;

public class DialogManager {
    public static void inputDialog(Context context) {
        Handler h = new Handler(Looper.getMainLooper());
        h.post(() -> {
            AlertDialog.Builder dialog= new AlertDialog.Builder(context);

            dialog.setTitle(context.getResources().getString(R.string.userNameDialogTitle));
            dialog.setMessage(context.getResources().getString(R.string.userNameDialogText));
            dialog.setCancelable(false);
            EditText userName = new EditText(context);
            dialog.setView(userName);

            dialog.setPositiveButton(R.string.dialogAccept, (dialogInterface, i) -> {
                MainActivity.userName = userName.getText().toString();
            });

            dialog.setNegativeButton(R.string.dialogCancel, (dialogInterface, i) -> {
                MainActivity.userName = "";
            });

            dialog.show();
        });
    }

    public static void confirmDialog(Context context, int score) {
        Handler h = new Handler(Looper.getMainLooper());
        h.post(() -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);

            dialog.setTitle(context.getResources().getString(R.string.endGameTitle));
            dialog.setMessage(context.getResources().getString(R.string.endGameText) + score);
            dialog.setCancelable(false);

            dialog.setNeutralButton(R.string.dialogAccept, (dialogToConfirm, id) -> {
                GameView.confirmed = true;
            });

            dialog.show();
        });
    }
}
