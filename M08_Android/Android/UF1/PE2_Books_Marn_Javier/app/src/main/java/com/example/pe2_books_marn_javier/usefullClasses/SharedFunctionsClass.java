package com.example.pe2_books_marn_javier.usefullClasses;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

public class SharedFunctionsClass extends AppCompatActivity  {
    private static final String SHARED_PREFERENCES = "com.example.pe2_books_marn_javier";
    private static final String CREATE_OPTION = "create";
    private static final String DELETE_OPTION = "delete";

    private static boolean confirmationDeleteDialog;
    private static boolean confirmationCreateDialog;

    private SharedFunctionsClass() {}

    private void saveData(Map<String, Integer> inputIDs, Context context) {

        if ("".equals(((EditText)findViewById(inputIDs.get("name"))).getText().toString())) {
            Toast.makeText(context, "The name cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            boolean confirm = true;
            SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();

            if ("".equals(((EditText)findViewById(inputIDs.get("name"))).getText().toString())) {
                confirmDialog(DELETE_OPTION, inputIDs, context);
                if (confirmationDeleteDialog) {
                    editor.remove(((EditText)findViewById(inputIDs.get("name"))).getText().toString());
                    Toast.makeText(context, "The phone has been deleted.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "The phone number didn't change.", Toast.LENGTH_SHORT).show();
                }
            } else {
                confirmDialog(CREATE_OPTION, inputIDs, context);
                if (confirmationCreateDialog) {
                    Toast.makeText(this, "Entry "
                            + ((int) sharedPref.getInt(((EditText)findViewById(inputIDs.get("name"))).getText().toString(), 0) == 0 ? "created" : "updated")
                            + " successfully!", Toast.LENGTH_SHORT).show();

                    editor.putInt(((EditText)findViewById(inputIDs.get("name"))).getText().toString(), Integer.parseInt(((EditText)findViewById(inputIDs.get("name"))).getText().toString()));
                } else {
                    Toast.makeText(context, "The phone has not been created!", Toast.LENGTH_SHORT).show();
                }
            }

            editor.commit();
        }
    }

    private void confirmDialog(String operation, Map<String, Integer> inputIDs, Context context) {
        AlertDialog.Builder dialogToConfirm = new AlertDialog.Builder(this);
        dialogToConfirm.setTitle("Important");
        dialogToConfirm.setMessage("Do you want to " + operation + (operation.equals(CREATE_OPTION) ? "/update" : "")
                + " the phone number of the "
                + ((EditText)findViewById(inputIDs.get("name"))).getText().toString()
                + " contact" + (operation.equals(CREATE_OPTION) ? " with this number: "
                + ((EditText)findViewById(inputIDs.get("name"))).getText().toString() : "")
                + "?");
        dialogToConfirm.setCancelable(false);
        dialogToConfirm.setPositiveButton("Confirm", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogToConfirm, int id) {
                        if (CREATE_OPTION.equals(operation)) {
                            confirmationCreateDialog = true;
                        } else {
                            confirmationDeleteDialog = true;
                        }
                    }
                });
        dialogToConfirm.setNegativeButton("Cancel", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogToConfirm, int id) {
                        if (CREATE_OPTION.equals(operation)) {
                            confirmationCreateDialog = false;
                        } else {
                            confirmationDeleteDialog = false;
                        }
                    }
                });
        dialogToConfirm.show();
    }

    private void getData(Map<String, Integer> inputIDs, Context context) {
        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        int phoneNumber = sharedPref.getInt((((EditText)findViewById(inputIDs.get("name"))).getText().toString()), 0);

        if (phoneNumber == 0) {
            Toast.makeText(this, "We dont have the " + (((EditText)findViewById(inputIDs.get("name")))).getText().toString() + " number.", Toast.LENGTH_SHORT).show();
        } else {
            (((EditText)findViewById(inputIDs.get("name")))).setText(String.format("%s", phoneNumber));
        }
    }
}
