package com.example.syncadapterexample;

import android.content.Context;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;

public class GamesAdapter extends SimpleCursorAdapter {
    public GamesAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }
}
