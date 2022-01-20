package com.javiermarin.uf1pe_marn_javier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.javiermarin.uf1pe_marn_javier.data.Data;
import com.javiermarin.uf1pe_marn_javier.results.ResultsActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar myToolbar;
    private EditText inputName;
    private EditText inputSurname;
    private EditText inputAddress;
    private Spinner spinnerProvince;
    private Spinner spinnerCity;
    private EditText inputZipCode;
    private EditText inputMail;
    private EditText inputPhone;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myToolbar = findViewById(R.id.myToolbar);
        myToolbar.setTitle("Postman");
        setSupportActionBar(myToolbar);

        findInputViews();
        findSpinnersViews();

        createSpinner(spinnerProvince, Data.BARCELONA_PROVINCES);
        createSpinner(spinnerCity, Data.BARCELONA_CITIES);

        setUtilitySubmitButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.totalImport:
                Toast.makeText(this, "Has seleccionado Importe total.", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.info:
                Toast.makeText(this, "M8. Prova 1 UF1 Javier Marín Guimerà", Toast.LENGTH_SHORT).show();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    private void findInputViews() {
        inputName = findViewById(R.id.inputName);
        inputSurname = findViewById(R.id.inputSurname);
        inputAddress = findViewById(R.id.inputAddress);
        inputZipCode = findViewById(R.id.inputZipCode);
        inputMail = findViewById(R.id.inputMail);
        inputPhone = findViewById(R.id.inputPhone);
    }

    private void findSpinnersViews() {
        spinnerProvince = findViewById(R.id.spinnerProvince);
        spinnerCity = findViewById(R.id.spinnerCity);
    }

    private void createSpinner(Spinner spinner, String[] dataArray) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Has seleccionado: " + spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                if (spinner.getId() == spinnerCity.getId()) {
                    inputZipCode.setText(Data.BARCELONA_ZIPS[position]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setUtilitySubmitButton() {
        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(view -> submitData());
    }

    private void submitData() {
        List<String> invalidData = validateData();

        if (invalidData.size() != 0) {
            /*
            * IMPORTANTE: HE INTENTADO PONER LOS DATOS DEL ERROR DENTRO DEL TOAST, PERO NO SÉ POR QUÉ FALLA EL String.join(). No me da tiempo a mirarlo más.
            */
            Toast.makeText(this, "Debes introducir datos en los campos siguientes: " /*+ String.join(", ", (String[])invalidData.toArray())*/ + ".", Toast.LENGTH_SHORT).show();
//            String resultText = "Debes introducir datos en los campos siguientes: " + String.join(", ", (String[])invalidData.toArray()) + ".";
        } else {
            Intent intent = new Intent(this, ResultsActivity.class);
            intent.putExtra("name", inputName.getText().toString());
            intent.putExtra("surname", inputSurname.getText().toString());
            intent.putExtra("address", inputAddress.getText().toString());
            intent.putExtra("zip", inputZipCode.getText().toString());
            intent.putExtra("mail", inputMail.getText().toString());
            intent.putExtra("phone", inputPhone.getText().toString());
            startActivity(intent);
        }
    }

    private List<String> validateData() {
        List<String> invalidData = new ArrayList<String>();

        if (inputName.getText().toString().equals("")) {
            invalidData.add("name");
        }

        if (inputSurname.getText().toString().equals("")) {
            invalidData.add("surname");
        }

        if (inputAddress.getText().toString().equals("")) {
            invalidData.add("address");
        }


        return invalidData;
    }
}