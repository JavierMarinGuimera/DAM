package com.javiermarin.sharedpreferences;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String SHARED_PREFERENCES = "com.javieremarin.sharedpreferences";
    private static final String CREATE_OPTION = "create";
    private static final String DELETE_OPTION = "delete";

    private static boolean confirmationDeleteDialog;
    private static boolean confirmationCreateDialog;

    private EditText inputName;
    private EditText inputPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFunctionality();
    }

    private void addFunctionality() {
        inputName = findViewById(R.id.inputName);
        inputPhone = findViewById(R.id.inputPhone);

        Button clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener(view -> clearInputs());

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(view -> saveData());

        Button getPhoneButton = findViewById(R.id.getPhoneButton);
        getPhoneButton.setOnClickListener(view -> getPhoneData());
    }

    private void clearInputs() {
        inputName.setText("");
        inputPhone.setText("");
        Toast.makeText(this, "Inputs cleared successfully", Toast.LENGTH_SHORT).show();
    }

    private void saveData() {
        if ("".equals(inputName.getText().toString())) {
            Toast.makeText(this, "The name cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();

            if ("".equals(inputPhone.getText().toString())) {
                confirmDialog(DELETE_OPTION);
                if (MainActivity.confirmationDeleteDialog) {
                    editor.remove(inputName.getText().toString());
                    Toast.makeText(this, "The phone has been deleted.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "The phone number didn't change.", Toast.LENGTH_SHORT).show();
                }
            } else {
                confirmDialog(CREATE_OPTION);
                if (confirmationCreateDialog) {
                Toast.makeText(this, "Entry " + ((int) sharedPref.getInt(inputName.getText().toString(), 0) == 0 ? "created" : "updated") + " successfully!", Toast.LENGTH_SHORT).show();

                editor.putInt(inputName.getText().toString(), Integer.parseInt(inputPhone.getText().toString()));
                } else {
                    Toast.makeText(this, "The phone has not been created!", Toast.LENGTH_SHORT).show();
                }
            }

            editor.commit();
        }
    }

    private void confirmDialog(String operation) {
        AlertDialog.Builder dialogToConfirm = new AlertDialog.Builder(this);
        dialogToConfirm.setTitle("Important");
        dialogToConfirm.setMessage("Do you want to " + operation + (operation.equals(CREATE_OPTION) ? "/update" : "") + " the phone number of the " + inputName.getText().toString() +
                " contact" + (operation.equals(CREATE_OPTION) ? " with this number: " + inputPhone.getText().toString() : "") + "?");
        dialogToConfirm.setCancelable(false);
        dialogToConfirm.setPositiveButton("Confirm", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogToConfirm, int id) {
                        if (CREATE_OPTION.equals(operation)) {
                            MainActivity.confirmationCreateDialog = true;
                        } else {
                            MainActivity.confirmationDeleteDialog = true;
                        }
                    }
                });
        dialogToConfirm.setNegativeButton("Cancel", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogToConfirm, int id) {
                        if (CREATE_OPTION.equals(operation)) {
                            MainActivity.confirmationCreateDialog = false;
                        } else {
                            MainActivity.confirmationDeleteDialog = false;
                        }
                    }
                });
        dialogToConfirm.show();
    }

    private void getPhoneData() {
        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        int phoneNumber = sharedPref.getInt(inputName.getText().toString(), 0);

        if (phoneNumber == 0) {
            Toast.makeText(this, "We dont have the " + inputName.getText().toString() + " number.", Toast.LENGTH_SHORT).show();
        } else {
            inputPhone.setText(String.format("%s", phoneNumber));
        }
    }
}