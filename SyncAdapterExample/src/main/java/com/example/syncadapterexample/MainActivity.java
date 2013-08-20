package com.example.syncadapterexample;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends FragmentActivity {

    public static final String ACCOUNT_TYPE = "com.example.syncadapterexample";
    public static final String ACCOUNT_NAME = "dummyaccount";
    private Account defaultAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        defaultAccount = createSyncAccount(this);
    }

    public void requestSync(View view) {
        Bundle requestParams = new Bundle();
        requestParams.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        requestParams.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        ContentResolver.requestSync(defaultAccount, GameProvider.AUTHORITY, requestParams);
    }

    public void addGame(View view) {
        String title = ((EditText)findViewById(R.id.title)).getText().toString();
        String year = ((EditText)findViewById(R.id.year)).getText().toString();
        addGame(title, Integer.parseInt(year));
    }

    private void addGame(String title, int creationYear) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(GameProvider.Games.TITLE, title);
        contentValues.put(GameProvider.Games.YEAR, creationYear);
        getContentResolver().insert(GameProvider.Games.CONTENT_URI, contentValues);
    }

    public static Account createSyncAccount(Context context) {
        Account newAccount = new Account(ACCOUNT_NAME, ACCOUNT_TYPE);

        AccountManager accountManager = (AccountManager) context.getSystemService(ACCOUNT_SERVICE);

        /*
         * Add the account and account type, no password or user data
         * If successful, return the Account object, otherwise report an error.
         */
        if (accountManager.addAccountExplicitly(newAccount, null, null)) {
            /*
             * If you don't set android:syncable="true" in
             * in your <provider> element in the manifest,
             * then call context.setIsSyncable(account, AUTHORITY, 1)
             * here.
             */
        } else {
            /*
             * The account exists or some other error occurred. Log this, report it,
             * or handle it internally.
             */
        }

        return newAccount;
    }

}
