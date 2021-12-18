package com.javiermarin.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.javiermarin.sqlite.dbClasses.StudentsSQLiteHelper;

import static com.javiermarin.sqlite.dbClasses.UsersDatabaseContract.StudentsTable;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // GLOBAL APP MESSAGES
    public static final String USER_NOT_FOUND = "The user is not on the database";
    public static final String USER_FOUND = "The user is already on the database.";
    public static final String NOT_EMPTY_INPUTS = "The id, name, and surname inputs cannot be empty for this operation.";
    private static final String QUERY_ERROR = " operation went wrong!";
    private static final String QUERY_SUCCESSFUL = " operation worked successfully!";
    private static final String NO_RESULTS = "We didn't found any results for this operation.";
    private static final String FILE_CREATED_SUCCESSFULLY = " file created successfully!";

    // RESULT FILE NAMES
    public static final String ASIX_FILE = "asixCycleFile.txt";
    public static final String DAM_FILE = "damCycleFile.txt";
    public static final String DAW_FILE = "dawCycleFile.txt";
    public static final String FIRST_COURSE_FILE = "firstCourseFile.txt";
    public static final String SECOND_COURSE_FILE = "secondCourseFile.txt";

    private static StudentsSQLiteHelper dbHelper;
    public enum find {ID, CYCLE, COURSE}

    private EditText inputId;
    private EditText inputName;
    private EditText inputSurname;
    private Spinner cycleSpinner;
    private RadioGroup courseGroup;
    private RadioButton firstRadioCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new StudentsSQLiteHelper(this);

        addFunctionality();
    }

    private void addFunctionality() {
        inputId = findViewById(R.id.inputId);
        inputName = findViewById(R.id.inputName);
        inputSurname = findViewById(R.id.inputSurname);

        cycleSpinner = findViewById(R.id.cycleSpinner);
        List<String> cycles = new ArrayList<>();
        cycles.add("ASIX");
        cycles.add("DAM");
        cycles.add("DAW");
        initSpinner(cycleSpinner, cycles);

        courseGroup = findViewById(R.id.courseGroup);
        firstRadioCourse = findViewById(R.id.firstRadioCourse);
        firstRadioCourse.setChecked(true);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(v -> insertUser());

        Button removeButton = findViewById(R.id.removeButton);
        removeButton.setOnClickListener(v -> deleteUser());

        Button updateButton = findViewById(R.id.updateButton);
        updateButton.setOnClickListener(v -> updateUser());

        Button findByDNI = findViewById(R.id.findByDNI);
        findByDNI.setOnClickListener(v -> findById());

        Button findByCycle = findViewById(R.id.findByCycle);
        findByCycle.setOnClickListener(v -> findByCycle());

        Button findByCourse = findViewById(R.id.findByCourse);
        findByCourse.setOnClickListener(v -> findByCourse());
    }

    private void initSpinner(Spinner spinner, List<String> spinnerData) {
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1, spinnerData);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, spinner.getItemAtPosition(position).toString() + " selected.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private Cursor selectUsers(find find) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                StudentsTable.COLUMN_ID_CARD,
                StudentsTable.COLUMN_NAME,
                StudentsTable.COLUMN_SURNAME,
                StudentsTable.COLUMN_CYCLE,
                StudentsTable.COLUMN_COURSE
        };

        String selection;
        if (find == MainActivity.find.ID) {
            selection = StudentsTable.COLUMN_ID_CARD + " = ?";
        } else if (find == MainActivity.find.CYCLE) {
            selection = StudentsTable.COLUMN_CYCLE + " = ?";
        } else {
            selection = StudentsTable.COLUMN_COURSE + " = ?";
        }

        String[] selectionArgs = {getInputData(find)};

        String sortOrder =
                StudentsTable.COLUMN_NAME + " DESC";

        return db.query(
                StudentsTable.TABLE,// The table to query
                projection,      // The array of columns to return (pass null to get all)
                selection,       // The columns for the WHERE clause
                selectionArgs,   // The values for the WHERE clause
                null,            // don't group the rows
                null,            // don't filter by row groups
                sortOrder        // The sort order
        );
    }

    private String getInputData(find find) {
        switch (find) {
            case ID:
                return inputId.getText().toString();

            case CYCLE:
                return cycleSpinner.getSelectedItem().toString();

            case COURSE:
                return ((RadioButton) (findViewById(courseGroup.getCheckedRadioButtonId()))).getText().toString();
            default:
                return "";
        }
    }

    private void showQueryMessage(long queryResult, String operation) {
        if (queryResult > 0) {
            Toast.makeText(this, operation.toUpperCase() + QUERY_SUCCESSFUL, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, operation.toUpperCase() + QUERY_ERROR, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkEmptyInputs() {
        return inputId.getText().toString().equals("") || inputName.getText().toString().equals("") || inputSurname.getText().toString().equals("");
    }

    private ContentValues getInputValuesToContentValues() {
        ContentValues values = new ContentValues();
        values.put(StudentsTable.COLUMN_ID_CARD, inputId.getText().toString());
        values.put(StudentsTable.COLUMN_NAME, inputName.getText().toString());
        values.put(StudentsTable.COLUMN_SURNAME, inputSurname.getText().toString());
        values.put(StudentsTable.COLUMN_CYCLE, cycleSpinner.getSelectedItem().toString());
        values.put(StudentsTable.COLUMN_COURSE, ((RadioButton) (findViewById(courseGroup.getCheckedRadioButtonId()))).getText().toString());
        return values;
    }

    private void insertUser() {
        Cursor cursor = selectUsers(find.ID);

        if (cursor.getCount() == 0) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            if (checkEmptyInputs()) {
                Toast.makeText(this, NOT_EMPTY_INPUTS, Toast.LENGTH_SHORT).show();
                return;
            }

            ContentValues values = getInputValuesToContentValues();

            long newRowId = db.insert(StudentsTable.TABLE, null, values);

            showQueryMessage(newRowId, "insert");
        } else {
            Toast.makeText(this, USER_FOUND, Toast.LENGTH_SHORT).show();
        }

    }

    private void updateUser() {
        Cursor cursor = selectUsers(find.ID);

        if (cursor.getCount() == 1) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            if (checkEmptyInputs()) {
                Toast.makeText(this, NOT_EMPTY_INPUTS, Toast.LENGTH_SHORT).show();
                return;
            }

            ContentValues values = getInputValuesToContentValues();

            String selection = StudentsTable.COLUMN_ID_CARD + " LIKE ?";
            String[] selectionArgs = {getInputData(find.ID)};

            int count = db.update(
                    StudentsTable.TABLE,
                    values,
                    selection,
                    selectionArgs);

            showQueryMessage(count, "update");
        } else {
            Toast.makeText(this, USER_NOT_FOUND, Toast.LENGTH_SHORT).show();
        }

    }

    private void deleteUser() {
        Cursor cursor = selectUsers(find.ID);

        if (cursor.getCount() == 1) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            String selection = StudentsTable.COLUMN_ID_CARD + " LIKE ?";
            String[] selectionArgs = {getInputData(find.ID)};
            int deletedRows = db.delete(StudentsTable.TABLE, selection, selectionArgs);

            showQueryMessage(deletedRows, "delete");
        } else {
            Toast.makeText(this, USER_NOT_FOUND, Toast.LENGTH_SHORT).show();
        }
    }

    private void findById() {
        Cursor cursor = selectUsers(find.ID);
        if (cursor.getCount() > 0) {
            generateFile(cursor, "idFile.txt");
        } else {
            Toast.makeText(this, NO_RESULTS, Toast.LENGTH_SHORT).show();
        }
    }

    private void findByCycle() {
        Cursor cursor = selectUsers(find.CYCLE);

        if (cursor.getCount() > 0) {
            switch (cycleSpinner.getSelectedItem().toString()) {
                case "ASIX":
                    generateFile(cursor, ASIX_FILE);
                    break;

                case "DAM":
                    generateFile(cursor, DAM_FILE);
                    break;

                case "DAW":
                    generateFile(cursor, DAW_FILE);
                    break;

                default:
                    Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    break;
            }
        } else {
            Toast.makeText(this, NO_RESULTS, Toast.LENGTH_SHORT).show();
        }
    }

    private void findByCourse() {
        Cursor cursor = selectUsers(find.COURSE);

        if (cursor.getCount() > 0) {
            if (((RadioButton)(findViewById(courseGroup.getCheckedRadioButtonId()))) == firstRadioCourse) {
                generateFile(cursor, FIRST_COURSE_FILE);
            } else {
                generateFile(cursor, SECOND_COURSE_FILE);
            }
        } else {
            Toast.makeText(this, NO_RESULTS, Toast.LENGTH_SHORT).show();
        }
    }

    private void generateFile(Cursor cursor, String file) {
        try {
            File saveFile = new File(getBaseContext().getFilesDir(), file);
            BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile));

            while (cursor.moveToNext()) {
                String line = "";
                line += cursor.getString(cursor.getColumnIndexOrThrow(StudentsTable.COLUMN_ID_CARD)) + ",";
                line += cursor.getString(cursor.getColumnIndexOrThrow(StudentsTable.COLUMN_NAME)) + ",";
                line += cursor.getString(cursor.getColumnIndexOrThrow(StudentsTable.COLUMN_SURNAME)) + ",";
                line += cursor.getString(cursor.getColumnIndexOrThrow(StudentsTable.COLUMN_CYCLE)) + ",";
                line += cursor.getString(cursor.getColumnIndexOrThrow(StudentsTable.COLUMN_COURSE)) + ".\n";

                writer.write(line);
            }

            Toast.makeText(this, file + FILE_CREATED_SUCCESSFULLY, Toast.LENGTH_SHORT).show();

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}