package com.example.syncadapter;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by adriankremski on 8/16/13.
 */
public class GameProvider extends ContentProvider{

    private GameDatabaseHelper gameDatabaseHelper = null;
    public static final String AUTHORITY = "com.example.syncadapter.gamesprovider";

    public static final class Games implements BaseColumns {
        public static final String ID = GameDatabaseHelper.ID_COLUMN_NAME;
        public static final String TITLE = GameDatabaseHelper.TITLE_COLUMN_NAME;
        public static final String YEAR = GameDatabaseHelper.YEAR_COLUMN_NAME;
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/games");
    }

    @Override
    public boolean onCreate() {
        gameDatabaseHelper = new GameDatabaseHelper(getContext());
        return gameDatabaseHelper == null ? false : true;
    }

    @Override
    public Cursor query(Uri url, String[] projection, String selection, String[] selectionArgs, String sort) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(GameDatabaseHelper.TABLE_NAME);

        Cursor cursorWithGames =
                queryBuilder.query(gameDatabaseHelper.getReadableDatabase(), projection, selection, selectionArgs, null, null, sort);

        cursorWithGames.setNotificationUri(getContext().getContentResolver(), url);

        return cursorWithGames;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri url, ContentValues contentValues) {
        long newRowId = gameDatabaseHelper.getWritableDatabase().insert(GameDatabaseHelper.TABLE_NAME, Games.TITLE, contentValues);

        if (newRowId > 0) {
            Uri uri= ContentUris.withAppendedId(GameProvider.Games.CONTENT_URI, newRowId);
            getContext().getContentResolver().notifyChange(uri, null);
            return uri;
        } else {
            return null;
        }
    }

    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {
        int count = gameDatabaseHelper.getWritableDatabase().delete(GameDatabaseHelper.TABLE_NAME, where, whereArgs);

        getContext().getContentResolver().notifyChange(uri, null);

        return count;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String where, String[] whereArgs) {
        int count= gameDatabaseHelper.getWritableDatabase().update(GameDatabaseHelper.TABLE_NAME,
                contentValues, where, whereArgs);

        getContext().getContentResolver().notifyChange(uri, null);

        return count;
    }
}
