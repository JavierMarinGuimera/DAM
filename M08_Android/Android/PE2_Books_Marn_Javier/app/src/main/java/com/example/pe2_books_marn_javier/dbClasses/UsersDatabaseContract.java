package com.example.pe2_books_marn_javier.dbClasses;

import android.provider.BaseColumns;

public class UsersDatabaseContract {
    private UsersDatabaseContract() {
    }

    public static class StudentsTable implements BaseColumns {
        public static final String TABLE = "students";
        public static final String COLUMN_ID_CARD = "id_card";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SURNAME = "surname";
        public static final String COLUMN_CYCLE = "cycle";
        public static final String COLUMN_COURSE = "course";
    }
}
