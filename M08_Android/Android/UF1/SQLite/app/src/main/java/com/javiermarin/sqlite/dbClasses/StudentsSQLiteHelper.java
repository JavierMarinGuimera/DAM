package com.javiermarin.sqlite.dbClasses;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.javiermarin.sqlite.dbClasses.UsersDatabaseContract.StudentsTable;

public class StudentsSQLiteHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Students.db";

    private static final String SQL_CREATE_TABLES =
            "CREATE TABLE " + StudentsTable.TABLE + " ("
                    + StudentsTable.COLUMN_ID_CARD + " TEXT PRIMARY KEY, "
                    + StudentsTable.COLUMN_NAME + " TEXT, "
                    + StudentsTable.COLUMN_SURNAME + " TEXT, "
                    + StudentsTable.COLUMN_CYCLE + " TEXT, "
                    + StudentsTable.COLUMN_COURSE + " TEXT)";

    private static final String SQL_DROP_TABLES =
            "DROP TABLE IF EXISTS " + StudentsTable.TABLE;

    public StudentsSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLES);
    }

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
