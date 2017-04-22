package scribble.kavzor.com.application.view.background;


import android.os.AsyncTask;

import java.util.ArrayList;

import scribble.kavzor.com.application.general.constant.AccessLevel;
import scribble.kavzor.com.application.general.exception.AccessLevelException;
import scribble.kavzor.com.application.general.pojo.model.Account;
import scribble.kavzor.com.application.general.pojo.model.Notebook;
import scribble.kavzor.com.application.general.pojo.model.Pair;

/**
 * Created by root on 2017-04-22.
 */

public class LoginThread extends AsyncTask<ArrayList<Pair<String, String>>, String, Account> {
    private final String LOGIN_URL = "";
    private final int FAKE_DELAY = 3000;

    /**
     * Asyncronous login background service
     * used to avoid pausing the main thread when accessing account data
     * @param credentials
     * @return
     */
    @Override
    protected Account doInBackground(ArrayList<Pair<String, String>>... credentials) {
        Account account = Account.getInstance();
        account.setAccessLevel(AccessLevel.LEVEL_SUPER_USER);
        account.setNotebook(new Notebook());
        try {
            account.setUsername("Developer");
        }
        catch(AccessLevelException exception){
            exception.printStackTrace();
        }

        publishProgress("Initalized login");

        try{                    //Used to simulate network delay
            publishProgress("Logging in");
            Thread.sleep(FAKE_DELAY);

        }
        catch(InterruptedException exception){
            exception.printStackTrace();
        }

        publishProgress("Logged in");
        return account;
    }


    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }
}
