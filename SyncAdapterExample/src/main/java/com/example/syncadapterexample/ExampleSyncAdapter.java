package com.example.syncadapterexample;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncResult;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.syncadapterexample.android.Game;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ExampleSyncAdapter extends AbstractThreadedSyncAdapter {

    private ContentResolver mContentResolver;
    private RequestQueue queue;

    public ExampleSyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        init(context);
    }

    public ExampleSyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        init(context);
    }

    private void init(Context context) {
        mContentResolver = context.getContentResolver();
        queue = Volley.newRequestQueue(context);
    }

    @Override
    public void onPerformSync(Account account, Bundle bundle, String s, ContentProviderClient contentProviderClient, SyncResult syncResult) {
        getContext().getContentResolver().delete(GameProvider.Games.CONTENT_URI, null, null);
    }
/*
    private void sendGamesToServer() {
        Cursor cursorWithGames = getContext().getContentResolver().query(GameProvider.Games.CONTENT_URI, null, null, null, null);
        List<Game> availableGames = getGamesFromCursor(cursorWithGames);

        if (cursorWithGames != null) {
            cursorWithGames.close();
        }

        for (int gameNumber = 0; gameNumber < availableGames.size(); ++ gameNumber) {
            sendGame(gameNumber, availableGames.size(), availableGames.get(gameNumber));
        }

    }

    private void sendGame(int gameNumber, int gamesAmount, final Game game) {

        JsonObjectRequest myReq = new JsonObjectRequest(Request.Method.POST, "http://192.168.21.161:3000/games", null,
                getResponseListener(gameNumber, gamesAmount), getErrorListener()) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return getGamePostParameters(game);
            }
        };

        queue.add(myReq);

    }

    private Response.Listener<JSONObject> getResponseListener(int gameNumber, int amountOfGames) {
        if (gameNumber == amountOfGames - 1)  {
            return new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    getContext().getContentResolver().delete(GameProvider.Games.CONTENT_URI,null, null);
                    downloadGamesFromServer();
                }
            };
        } else {
            return null;
        }
    }

    private Response.ErrorListener getErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               Log.i("Error", error.getLocalizedMessage());
            }
        };
    }

    private Map<String, String> getGamePostParameters(Game game) {
        Map<String, String> postParameters = new HashMap<String, String>();
        postParameters.put("title", game.getTitle());
        postParameters.put("year", String.valueOf(game.getYear()));
        return postParameters;
    }

    private void downloadGamesFromServer() {
       getContext().getContentResolver().notifyChange(GameProvider.Games.CONTENT_URI, null);
    }

    private List<Game> getGamesFromCursor(Cursor cursor) {
        if (cursor != null && cursor.moveToFirst())  {
            List<Game> games = new LinkedList<Game>();

            games.add(getSingleGameFromCursor(cursor));

            while(cursor.moveToNext()) {
                games.add(getSingleGameFromCursor(cursor));
            }

            return games;
        }
        return new LinkedList<Game>();
    }

    private Game getSingleGameFromCursor(Cursor cursor) {
        String title = cursor.getString(cursor.getColumnIndex(GameProvider.Games.TITLE));
        int year = cursor.getInt(cursor.getColumnIndex(GameProvider.Games.YEAR));
        return new Game(title, year);
    }

    private void addGame(String title, int creationYear) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(GameProvider.Games.TITLE, title);
        contentValues.put(GameProvider.Games.YEAR, creationYear);
        getContext().getContentResolver().insert(GameProvider.Games.CONTENT_URI, contentValues);
    }*/
}