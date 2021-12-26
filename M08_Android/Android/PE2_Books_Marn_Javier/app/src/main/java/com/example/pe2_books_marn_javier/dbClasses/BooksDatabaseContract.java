package com.example.pe2_books_marn_javier.dbClasses;

import android.provider.BaseColumns;

public class BooksDatabaseContract {
    private BooksDatabaseContract() {
    }

    public static class BooksTable implements BaseColumns {
        public static final String TABLE = "books";
        public static final String COLUMN_ISBN = "isbn";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_AUTHOR = "author";
    }
}
