package com.javiermarin.uf1pe_marn_javier.results;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.javiermarin.uf1pe_marn_javier.R;

import org.w3c.dom.Text;

public class ResultsActivity extends AppCompatActivity {

    private TextView nameResult;
    private TextView surnameResult;
    private TextView addressResult;
    private TextView zipCodeResult;
    private TextView mailResult;
    private TextView phoneResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        nameResult = findViewById(R.id.nameResult);
        surnameResult = findViewById(R.id.surnameResult);
        addressResult = findViewById(R.id.addressResult);
        zipCodeResult = findViewById(R.id.zipCodeResult);
        mailResult = findViewById(R.id.mailResult);
        phoneResult = findViewById(R.id.phoneResult);

        nameResult.setText(getIntent().getExtras().get("name").toString());
        surnameResult.setText(getIntent().getExtras().get("surname").toString());
        addressResult.setText(getIntent().getExtras().get("address").toString());
        zipCodeResult.setText(getIntent().getExtras().get("zip").toString());
        mailResult.setText(getIntent().getExtras().get("mail").toString());
        phoneResult.setText(getIntent().getExtras().get("phone").toString());
    }
}