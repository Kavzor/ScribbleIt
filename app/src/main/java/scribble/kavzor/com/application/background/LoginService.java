package scribble.kavzor.com.application.background;


import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.RunnableFuture;

import scribble.kavzor.com.application.general.constant.AccessLevel;
import scribble.kavzor.com.application.general.exception.AccessLevelException;
import scribble.kavzor.com.application.general.network.Connection;
import scribble.kavzor.com.application.general.pojo.model.Account;
import scribble.kavzor.com.application.general.pojo.model.Notebook;
import scribble.kavzor.com.application.general.pojo.model.Pair;
import scribble.kavzor.com.application.view.MainActivity;

/**
 * Created by root on 2017-04-22.
 */

public class LoginService implements Runnable{
    private final String LOGIN_ADRESS = "";
    private final int FAKE_DELAY = 3000;

    private String status;

    public LoginService(){
        this.status = "Not logged in";
    }


    /**
     * Asyncronous login background service
     * used to avoid pausing the main thread when accessing account data
    */
    @Override
    public void run() {
        setStatus("Initalized login");

        if(MainActivity.isDeveloperMode) {
            developerLogin();
            setStatus("Logged in");
        }
        else {
            //login();
            setStatus("Failed to login");
        }
    }

    public void login() {
        Account account = Account.getInstance();
        String username = account.getUsername();
        String password = account.getPassword();

        JsonReader reader = null;
        Gson gson = new GsonBuilder().create();

        ArrayList<Pair<String, String>> params = new ArrayList<>();
        params.add(new Pair<>("username", username));
        params.add(new Pair<>("password", password));

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        params.add(new Pair<>("lastLogin", simpleDateFormat.format(date)));


        try {
            Connection connection = new Connection();
            reader = connection.getReader(LOGIN_ADRESS, params);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        if (reader != null){
            account.setAccount((Account) gson.fromJson(reader, Account.class));
        }
    }

    private void developerLogin(){

        try{                    //Used to simulate network delay
            setStatus("Logging in");
            Thread.sleep(FAKE_DELAY);

        }
        catch(InterruptedException exception){
            exception.printStackTrace();
        }


        Account account = Account.getInstance();
        account.setAccessLevel(AccessLevel.LEVEL_SUPER_USER);
        account.setNotebook(new Notebook());
        try {
            account.setUsername("Developer");
        }
        catch(AccessLevelException exception){
            exception.printStackTrace();
        }
    }

    private void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }
}
