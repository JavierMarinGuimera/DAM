package com.example.pe2_books_marn_javier.usefullClasses;

import static com.example.pe2_books_marn_javier.dbClasses.BooksDatabaseContract.BooksTable;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileFunctionsClass {

    private static final String FILE_CREATED_SUCCESSFULLY = " file created successfully!";

    private FileFunctionsClass() {}

    public static void generateFileFromCursor(Cursor cursor, String file, Context context) {
        try {
            File saveFile = new File(context.getFilesDir(), file);
            BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile));

            while (cursor.moveToNext()) {
                String line = "";
                line += cursor.getString(cursor.getColumnIndexOrThrow(BooksTable.COLUMN_ISBN)) + ",";
                line += cursor.getString(cursor.getColumnIndexOrThrow(BooksTable.COLUMN_TITLE)) + ",";
                line += cursor.getString(cursor.getColumnIndexOrThrow(BooksTable.COLUMN_AUTHOR)) + ".\n";

                writer.write(line);
            }

            Toast.makeText(context, file + FILE_CREATED_SUCCESSFULLY, Toast.LENGTH_SHORT).show();

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
