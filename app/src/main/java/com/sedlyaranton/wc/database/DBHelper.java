package com.sedlyaranton.wc.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

   public static final int DATABASE_VERSION = 1;
   public static final String DATABASE_NAME = "reportCardDb";
   public static final String TABLE_REPORTCARD = "reportCard";

   public static final String COLUMN_ID = "id";
   public static final String COLUMN_DATE = "date";
   public static final String COLUMN_CLOCK = "clock";
   public static final String COLUMN_TEXT = "text";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE reportCard (" + COLUMN_ID
                + " INTRGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_DATE + " TEXT,"
                + COLUMN_CLOCK + " TEXT," + COLUMN_TEXT + " TEXT);");
        // ДОБАВЛЕНИЕ НАЧАЛЬНЫХ ДАННЫХ
        db.execSQL("INSERT INTO "+ TABLE_REPORTCARD +" (" + COLUMN_DATE + ", " +
                COLUMN_CLOCK + ", " + COLUMN_TEXT + ")VALUES ('TEST', 'TEST','TEST');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPORTCARD);

        onCreate(db);

    }


}
