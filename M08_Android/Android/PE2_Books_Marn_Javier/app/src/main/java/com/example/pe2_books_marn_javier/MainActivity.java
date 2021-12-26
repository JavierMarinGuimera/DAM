package com.example.pe2_books_marn_javier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pe2_books_marn_javier.dbClasses.BooksSQLiteHelper;
import com.example.pe2_books_marn_javier.dbClasses.BooksDatabaseContract.BooksTable;

public class MainActivity extends AppCompatActivity {

    /*
    * EL MÉTODO PARA ACTUALIZAR LA LISTA NO ME HA DADO TIEMPO PERO, SI COMPRUEBAS, VERÁS QUE SOLO ME FALTA METER EL TEXTVIEW EN LA LISTVIEW
    * QUE ES LO QUE NO ME HA DADO TIEMPO PORQUE NO HE SABIDO :(
    */

    // GLOBAL APP MESSAGES
    public static final String BOOK_NOT_FOUND = "The book is not on the database";
    public static final String BOOK_FOUND = "The book is already on the database.";
    public static final String NOT_EMPTY_INPUTS = "The id, name, and surname inputs cannot be empty for this operation.";
    private static final String QUERY_ERROR = " operation went wrong!";
    private static final String QUERY_SUCCESSFUL = " operation worked successfully!";
    private static final String NO_RESULTS = "We didn't found any results for this operation.";

    public enum find {EVERYONE, ISBN}

    public EditText isbn;
    public EditText title;
    public EditText author;
    public ListView bookResults;
    public Button clearButton;
    public Button addButton;
    public Button removeButton;
    public Button modifyButton;

    public BooksSQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new BooksSQLiteHelper(this);

        addFunctionality();

        readDataBase();
    }

    private void readDataBase() {
        Cursor cursor = selectBooks(find.EVERYONE);

        if (cursor.getCount() > 0) {
            updateBookList(cursor);
        } else {
            Toast.makeText(this, "We dont have records on the ddbb yet!", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateBookList(Cursor cursor) {
        Toast.makeText(this, "updateBookList", Toast.LENGTH_SHORT).show();
        while (cursor.moveToNext()) {
            Toast.makeText(this, "while", Toast.LENGTH_SHORT).show();
            TextView text = new TextView(this);

            String line = "";
            line += cursor.getString(cursor.getColumnIndexOrThrow(BooksTable.COLUMN_TITLE)) + "\n";
            line += cursor.getString(cursor.getColumnIndexOrThrow(BooksTable.COLUMN_ISBN));

            text.setText(line);

//            bookResults.addView((View) text);
        }
    }

    private void addFunctionality() {
        isbn = findViewById(R.id.isbn);
        title = findViewById(R.id.title);
        author = findViewById(R.id.author);

        bookResults = findViewById(R.id.bookResults);
        TextView text = new TextView(getBaseContext());
        text.setText("Catalog");
        bookResults.addHeaderView(text);

        clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener(v -> clearInputs());

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(v -> insertBook());

        removeButton = findViewById(R.id.removeButton);
        removeButton.setOnClickListener(v -> deleteBook());

        modifyButton = findViewById(R.id.modifyButton);
        modifyButton.setOnClickListener(v -> updateBook());
    }

    private void clearInputs() {
        isbn.setText("");
        title.setText("");
        author.setText("");
        Toast.makeText(this, "Inputs cleared", Toast.LENGTH_SHORT).show();
    }

    private boolean checkEmptyInputs() {
        return isbn.getText().toString().equals("") || title.getText().toString().equals("") || author.getText().toString().equals("");
    }

    private ContentValues getInputValuesToContentValues() {
        ContentValues values = new ContentValues();
        values.put(BooksTable.COLUMN_ISBN, isbn.getText().toString());
        values.put(BooksTable.COLUMN_TITLE, title.getText().toString());
        values.put(BooksTable.COLUMN_AUTHOR, author.getText().toString());
        return values;
    }

    private void showQueryMessage(long queryResult, String operation) {
        if (queryResult > 0) {
            Toast.makeText(this, operation.toUpperCase() + QUERY_SUCCESSFUL, Toast.LENGTH_SHORT).show();
            readDataBase();
        } else {
            Toast.makeText(this, operation.toUpperCase() + QUERY_ERROR, Toast.LENGTH_SHORT).show();
        }
    }

    private Cursor selectBooks(find find) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        if (find == MainActivity.find.EVERYONE) {
            return db.query(
                    BooksTable.TABLE,// The table to query
                    null,      // The array of columns to return (pass null to get all)
                    null,       // The columns for the WHERE clause
                    null,   // The values for the WHERE clause
                    null,            // don't group the rows
                    null,            // don't filter by row groups
                    null);
        }

        String[] projection = new String[]{
                    BooksTable.COLUMN_ISBN,
                    BooksTable.COLUMN_TITLE,
                    BooksTable.COLUMN_AUTHOR,
            };
        String selection = BooksTable.COLUMN_ISBN + " = ?";

        String[] selectionArgs = {getInputData(find)};

        String sortOrder =
                BooksTable.COLUMN_TITLE + " ASC";

        return db.query(
                BooksTable.TABLE,// The table to query
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
            case EVERYONE:
                return "*";

            case ISBN:
                return isbn.getText().toString();

            default:
                return "";
        }
    }

    private void insertBook() {
        Cursor cursor = selectBooks(find.ISBN);

        if (cursor.getCount() == 0) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            if (checkEmptyInputs()) {
                Toast.makeText(this, NOT_EMPTY_INPUTS, Toast.LENGTH_SHORT).show();
                return;
            }

            ContentValues values = getInputValuesToContentValues();

            long newRowId = db.insert(BooksTable.TABLE, null, values);

            showQueryMessage(newRowId, "insert");
        } else {
            Toast.makeText(this, BOOK_FOUND, Toast.LENGTH_SHORT).show();
        }

    }

    private void updateBook() {
        Cursor cursor = selectBooks(find.ISBN);

        if (cursor.getCount() == 1) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            if (checkEmptyInputs()) {
                Toast.makeText(this, NOT_EMPTY_INPUTS, Toast.LENGTH_SHORT).show();
                return;
            }

            ContentValues values = getInputValuesToContentValues();

            String selection = BooksTable.COLUMN_ISBN + " LIKE ?";
            String[] selectionArgs = {getInputData(find.ISBN)};

            int count = db.update(
                    BooksTable.TABLE,
                    values,
                    selection,
                    selectionArgs);

            showQueryMessage(count, "update");
        } else {
            Toast.makeText(this, BOOK_NOT_FOUND, Toast.LENGTH_SHORT).show();
        }

    }

    private void deleteBook() {
        Cursor cursor = selectBooks(find.ISBN);

        if (cursor.getCount() == 1) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            String selection = BooksTable.COLUMN_ISBN + " LIKE ?";
            String[] selectionArgs = {getInputData(find.ISBN)};
            int deletedRows = db.delete(BooksTable.TABLE, selection, selectionArgs);

            showQueryMessage(deletedRows, "delete");
        } else {
            Toast.makeText(this, BOOK_NOT_FOUND, Toast.LENGTH_SHORT).show();
        }
    }
}