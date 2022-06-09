package ru.mirea.ochirgoryaeva.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.FileUtils;
import android.widget.ProgressBar;

public class MyProgressDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        ProgressDialog progressDialog =  new ProgressDialog(this);
       //progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Progress Title");
        progressDialog.setMessage("Loading...");
        progressDialog.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                ((MainActivity)getActivity()).onOk2Clicked();
                dialog.cancel();
            }
        });
        return progressDialog.onStart();
    }
}
