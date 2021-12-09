package com.javiermarin.cars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import classes.Car;

public class MainActivity extends AppCompatActivity {
    private static final String SAVE_FILE = "last_car.txt";
    private static final String SERIES = "series";
    private static final String CAR = "car";
    private static final String SHIFT = "shift";
    private static final String FUEL = "fuel";
    private static final String METALLIC = "metallic";
    private static final String LEATHER = "leather";
    private static final String NAVIGATION = "navigation";

    private List<String> series = new ArrayList<>();
    private List<List<Car>> seriesCars = new ArrayList<>();
    private List<Integer> extras = new ArrayList<>();
    private Context context;
    private Boolean overrideSpecs;

    private Spinner seriesSpinner;
    private Spinner modelsSpinner;
    private ImageView carImg;
    private RadioGroup shiftRadio;
    private RadioButton automaticRadioButton;
    private RadioGroup fuelRadio;
    private RadioButton dieselRadioButton;
    private CheckBox metallicCheck;
    private CheckBox leatherCheck;
    private CheckBox navigationCheck;
    private final Map<String, Integer> selectedSpecs = new HashMap<>();
    private TextView moneyResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getBaseContext();

        readAndStoreData();
        loadLastCar();
        addFunctionality();
        calculatePrice();
    }

    private void readAndStoreData() {
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.datos_coches);
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(inputStream));

            String line;

            // Series loop
            while (!(line = bufferedReader.readLine()).equals("end")) {
                series.add(line);
            }

            line = bufferedReader.readLine();

            // Cars loop
            for (int i = 0; i < series.size(); i++) {
                List<Car> cars = new ArrayList<>();
                while (!line.equals("end")) {
                    List<String> carData = new ArrayList<>();
                    for (int j = 0; j < 3; j++) {
                        carData.add(line);
                        line = bufferedReader.readLine();
                    }

                    String model = carData.get(0);
                    String price = carData.get(1);
                    String img = carData.get(2);

                    cars.add(new Car(model, price, img));
                }
                line = bufferedReader.readLine();
                seriesCars.add(cars);
            }

            // Extras loop
            while (line != null) {
                extras.add(Integer.parseInt(line));
                line = bufferedReader.readLine();
            }

            bufferedReader.close();
            inputStream.close();
        } catch (Exception ex) {
            Log.e("Ficheros", " Error en leer fichero desde recurso raw.");
        }
    }

    private void addFunctionality() {
        seriesSpinner = findViewById(R.id.seriesSpinner);
        modelsSpinner = findViewById(R.id.modelsSpinner);

        carImg = findViewById(R.id.carImg);

        shiftRadio = findViewById(R.id.shiftRadio);
        automaticRadioButton = findViewById(R.id.automaticRadioButton);
        shiftRadio.setOnCheckedChangeListener((radioGroup, i) -> changeSelectedSpecs(SHIFT));

        fuelRadio = findViewById(R.id.fuelRadio);
        dieselRadioButton = findViewById(R.id.dieselRadioButton);
        fuelRadio.setOnCheckedChangeListener((radioGroup, i) -> changeSelectedSpecs(FUEL));

        metallicCheck = findViewById(R.id.metallicCheck);
        metallicCheck.setOnCheckedChangeListener((compoundButton, b) -> changeSelectedSpecs(METALLIC));

        leatherCheck = findViewById(R.id.leatherCheck);
        leatherCheck.setOnCheckedChangeListener((compoundButton, b) -> changeSelectedSpecs(LEATHER));

        navigationCheck = findViewById(R.id.navigationCheck);
        navigationCheck.setOnCheckedChangeListener((compoundButton, b) -> changeSelectedSpecs(NAVIGATION));

        initSpinner(seriesSpinner, series);

        moneyResult = findViewById(R.id.moneyResult);

        loadLastStatus();
    }

    private void initSpinner(Spinner spinner, List<String> spinnerData) {
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, spinnerData);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinner.getId() == seriesSpinner.getId()) {
                    selectedSpecs.put(SERIES, position);
                    if (overrideSpecs) {
                        selectedSpecs.put(CAR, 0);
                    } else {
                        overrideSpecs = true;
                    }
                    initSpinner(modelsSpinner, modelsToStringList(seriesCars.get(position)));
                } else {
                    selectedSpecs.put(CAR, position);
                    loadIMG();
                    calculatePrice();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner.setSelection((spinner.getId() == seriesSpinner.getId()) ? selectedSpecs.get(SERIES) : selectedSpecs.get(CAR));
    }

    private List<String> modelsToStringList(List<Car> cars) {
        List<String> models = new ArrayList<>();
        for (Car singleCar : cars) {
            models.add(singleCar.getModel());
        }
        return models;
    }

    private void loadIMG() {
        String img = seriesCars.get(selectedSpecs.get(SERIES)).get(selectedSpecs.get(CAR)).getImg();
        carImg.setImageResource(getResources().getIdentifier(img, "drawable",
                getPackageName()));
    }

    private void saveLastCar() {
        try {
            File saveFile = new File(context.getFilesDir(), SAVE_FILE);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(saveFile));

            bufferedWriter.write(Integer.toString(selectedSpecs.get(SERIES)) + "\n");
            bufferedWriter.write(Integer.toString(selectedSpecs.get(CAR)) + "\n");
            bufferedWriter.write(Integer.toString(selectedSpecs.get(SHIFT)) + "\n");
            bufferedWriter.write(Integer.toString(selectedSpecs.get(FUEL)) + "\n");
            bufferedWriter.write(Integer.toString(selectedSpecs.get(METALLIC)) + "\n");
            bufferedWriter.write(Integer.toString(selectedSpecs.get(LEATHER)) + "\n");
            bufferedWriter.write(Integer.toString(selectedSpecs.get(NAVIGATION)) + "\n");

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void loadLastCar() {
        try {
            if (new File(context.getFilesDir(), SAVE_FILE).exists()) {
                File loadFile = new File(context.getFilesDir(), SAVE_FILE);
                BufferedReader bufferedReader = new BufferedReader(new FileReader(loadFile));
                String line;

                line = bufferedReader.readLine();
                selectedSpecs.put(SERIES, Integer.parseInt(line));

                line = bufferedReader.readLine();
                selectedSpecs.put(CAR, Integer.parseInt(line));

                line = bufferedReader.readLine();
                selectedSpecs.put(SHIFT, Integer.parseInt(line));

                line = bufferedReader.readLine();
                selectedSpecs.put(FUEL, Integer.parseInt(line));

                line = bufferedReader.readLine();
                selectedSpecs.put(METALLIC, Integer.parseInt(line));

                line = bufferedReader.readLine();
                selectedSpecs.put(LEATHER, Integer.parseInt(line));

                line = bufferedReader.readLine();
                selectedSpecs.put(NAVIGATION, Integer.parseInt(line));

                bufferedReader.close();
            } else {
                selectedSpecs.put(SERIES, 0);
                selectedSpecs.put(CAR, 0);
                selectedSpecs.put(NAVIGATION, 0);
                selectedSpecs.put(FUEL, 0);
                selectedSpecs.put(SHIFT, 0);
                selectedSpecs.put(METALLIC, 0);
                selectedSpecs.put(LEATHER, 0);
            }

            overrideSpecs = false;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadLastStatus() {
        if (selectedSpecs.get(SHIFT) > 0) {
            automaticRadioButton.setChecked(true);
        }

        if (selectedSpecs.get(FUEL) > 0) {
            dieselRadioButton.setChecked(true);
        }

        if (selectedSpecs.get(METALLIC) > 0) {
            metallicCheck.setChecked(true);
        }

        if (selectedSpecs.get(LEATHER) > 0) {
            leatherCheck.setChecked(true);
        }

        if (selectedSpecs.get(NAVIGATION) > 0) {
            navigationCheck.setChecked(true);
        }
    }

    private void changeSelectedSpecs(String key) {
        if (key.equals(SHIFT)) {
            if (shiftRadio.getCheckedRadioButtonId() == automaticRadioButton.getId()) {
                selectedSpecs.put(key, extras.get(0));
            } else {
                selectedSpecs.put(key, 0);
            }
        }

        if (key.equals(FUEL)) {
            if (fuelRadio.getCheckedRadioButtonId() == dieselRadioButton.getId()) {
                selectedSpecs.put(key, extras.get(1));
            } else {
                selectedSpecs.put(key, 0);
            }
        }

        if (key.equals(METALLIC)) {
            if (metallicCheck.isChecked()) {
                selectedSpecs.put(key, extras.get(2));
            } else {
                selectedSpecs.put(key, 0);
            }
        }

        if (key.equals(LEATHER)) {
            if (leatherCheck.isChecked()) {
                selectedSpecs.put(key, extras.get(3));
            } else {
                selectedSpecs.put(key, 0);
            }
        }

        if (key.equals(NAVIGATION)) {
            if (navigationCheck.isChecked()) {
                selectedSpecs.put(key, extras.get(4));
            } else {
                selectedSpecs.put(key, 0);
            }
        }

        calculatePrice();
    }

    private void calculatePrice() {
        int modelPrice = Integer.parseInt(seriesCars.get(selectedSpecs.get(SERIES)).get(selectedSpecs.get(CAR)).getPrice());
        int shift = selectedSpecs.get(SHIFT);
        int fuel = selectedSpecs.get(FUEL);
        int metallicPaint = selectedSpecs.get(METALLIC);
        int leatherSeating = selectedSpecs.get(LEATHER);
        int navigationSystem = selectedSpecs.get(NAVIGATION);

        int finalPrice = modelPrice + shift + fuel + metallicPaint + leatherSeating + navigationSystem;

        moneyResult.setText(Integer.toString(finalPrice));

        saveLastCar();
    }
}