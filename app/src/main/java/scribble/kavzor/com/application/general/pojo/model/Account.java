package scribble.kavzor.com.application.general.pojo.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import scribble.kavzor.com.application.general.constant.AccessLevel;
import scribble.kavzor.com.application.general.exception.AccessLevelException;


/**
 * Created by root on 2017-03-08.
 */

public class Account {

    private static Account instance;


    @SerializedName("userID")
    private int userID;

    @SerializedName("username")
    private String username;

    @SerializedName("lastLogin")
    private String lastLogin;

    private transient String password;

    @SerializedName("accessLevel")
    private AccessLevel accessLevel;

    @SerializedName("ipAdress")
    private String ipAdress;

    @SerializedName("noteBook")
    private Notebook notebook;



    private Account(){
        this.userID = 0;
        this.username = "Guest";
        this.password = "";
        this.accessLevel = AccessLevel.LEVEL_NORMAL_USER;
    }

    public static Account getInstance(){
        if(instance == null){
            instance = new Account();
        }
        return instance;
    }

    public Account getAccount(){
        return instance;
    }

    public void setAccount(Account account){
        if(instance == null){
            getInstance();
        }
        if(account.getId() > 0){
            this.userID = account.getId();
        }
        this.username = account.getUsername();
        this.lastLogin = account.getLastLogin();
        this.accessLevel = account.getAccessLevel();
        this.ipAdress = account.getIpAdress();
        this.notebook = account.getNotebook();
    }

    public Notebook getNotebook() {
        return notebook;
    }

    public void setNotebook(Notebook notebook) {
        this.notebook = notebook;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void setId(int id) {
        this.userID = id;
    }

    public int getId() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws AccessLevelException {
        if(accessLevel != AccessLevel.LEVEL_GUEST_USER) {
            this.username = username;
        }
        else {
            throw new AccessLevelException("AccountService must be logged in to change username");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws AccessLevelException{
        if(accessLevel != AccessLevel.LEVEL_GUEST_USER){
            this.password = password;
        }
        else {
            throw new AccessLevelException("AccountService must be logged in to change password");
        }
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getJsonFormat(){
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

    public void resetAccountSession(){
        instance = null;
    }

    public static boolean isLoggedIn(){
        return instance != null;
    }

    @Override
    public String toString() {
        return "AccountURLs{" +
                "id=" + userID +
                ", username='" + username + '\'' +
                ", lastLogin='" + lastLogin + '\'' +
                ", password='" + password + '\'' +
                ", accessLevel=" + accessLevel +
                ", ipAdress='" + ipAdress + '\'' +
                ", notebook=" + notebook +
                '}';
    }
}
