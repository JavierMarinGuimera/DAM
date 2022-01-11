package com.example.pe2_books_marn_javier.dbClasses;

import static com.example.pe2_books_marn_javier.dbClasses.BooksDatabaseContract.BooksTable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BooksSQLiteHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "books.db";

    private static final String SQL_CREATE_TABLES =
            "CREATE TABLE " + BooksTable.TABLE + " ("
                    + BooksTable.COLUMN_ISBN + " TEXT PRIMARY KEY, "
                    + BooksTable.COLUMN_TITLE + " TEXT, "
                    + BooksTable.COLUMN_AUTHOR + " TEXT)";

    private static final String SQL_DROP_TABLES =
            "DROP TABLE IF EXISTS " + BooksTable.TABLE;

    public BooksSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {db.execSQL(SQL_CREATE_TABLES);}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_TABLES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
