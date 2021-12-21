package com.example.pe2_books_marn_javier.usefullClasses;

import static com.example.pe2_books_marn_javier.dbClasses.UsersDatabaseContract.StudentsTable;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class fileFunctionsClass {

    private static final String FILE_CREATED_SUCCESSFULLY = " file created successfully!";

    private fileFunctionsClass() {}

    public static void generateFileFromCursor(Cursor cursor, String file, Context context) {
        try {
            File saveFile = new File(context.getFilesDir(), file);
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

            Toast.makeText(context, file + FILE_CREATED_SUCCESSFULLY, Toast.LENGTH_SHORT).show();

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
