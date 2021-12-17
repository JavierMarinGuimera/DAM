package com.javiermarin.sqlite.dbClasses;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.javiermarin.sqlite.dbClasses.UsersDatabaseContract.UsersTable;

public class UsersSQLiteHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Users.db";

    private static final String SQL_CREATE_TABLES =
            "CREATE TABLE " + UsersTable.TABLE + " ("
                    + UsersTable._ID + " INTEGER PRIMARY KEY, "
                    + UsersTable.COLUMN_NAME + " TEXT)";

    private static final String SQL_DROP_TABLES =
            "DROP TABLE IF EXISTS " + UsersTable.TABLE;

    public UsersSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DROP_TABLES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
