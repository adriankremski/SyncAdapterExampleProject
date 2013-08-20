package com.example.syncadapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GameDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "games.db";
    public static final String TABLE_NAME = "games";
    public static final String ID_COLUMN_NAME = "_id";
    public static final String TITLE_COLUMN_NAME = "title";
    public static final String YEAR_COLUMN_NAME = "year";

    public GameDatabaseHelper(Context context) {
       super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME +
                " (" + ID_COLUMN_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TITLE_COLUMN_NAME + " TEXT, "
                + YEAR_COLUMN_NAME + " INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int i2) {
        sqlitedatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqlitedatabase);
    }
}
