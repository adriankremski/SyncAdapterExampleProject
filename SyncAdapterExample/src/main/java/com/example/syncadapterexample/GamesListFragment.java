package com.example.syncadapterexample;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.widget.SimpleCursorAdapter;

public class GamesListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private SimpleCursorAdapter gamesAdaper;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(0, null, this);

        String[] from = new String[]{ GameProvider.Games.TITLE, GameProvider.Games.YEAR };
        int[] to = new int[] { R.id.title, R.id.year };

        gamesAdaper = new GamesAdapter(getActivity(), R.layout.game_row, null, from, to, 0);

        setListAdapter(gamesAdaper);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = { GameProvider.Games.ID, GameProvider.Games.TITLE, GameProvider.Games.YEAR};

        CursorLoader gamesLoader = new CursorLoader(getActivity(), GameProvider.Games.CONTENT_URI, projection, null, null, null);

        return gamesLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        gamesAdaper.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        gamesAdaper.swapCursor(null);
    }
}
