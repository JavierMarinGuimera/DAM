package com.javiermarin.sqlite.dbClasses;

import android.provider.BaseColumns;

public class UsersDatabaseContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private UsersDatabaseContract() {
    }

    /* Inner class that defines the table contents */
    public static class UsersTable implements BaseColumns {
        public static final String TABLE = "users";
        public static final String COLUMN_NAME = "name";
    }
}
