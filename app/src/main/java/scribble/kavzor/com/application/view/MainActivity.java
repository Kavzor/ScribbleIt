package scribble.kavzor.com.application.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import scribble.kavzor.com.application.R;
import scribble.kavzor.com.application.background.LoginService;
import scribble.kavzor.com.application.general.pojo.model.Account;

public class MainActivity extends AppCompatActivity {
    public static boolean isDeveloperMode = false;
    public static final String SCRIBBLE_IT_LOGGER_TAG = "scribble_error";

    private TextView loginStatus;

    private LoginService loginService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginStatus = (TextView) findViewById(R.id.login_status);
        loginService = new LoginService();

        initUI();
    }

    private void initUI(){
        updateLoginStatus();
        Account.getInstance().resetAccountSession();


        CheckBox developerBox = (CheckBox) findViewById(R.id.developer_box);
        developerBox.setOnClickListener(v -> {
            if(developerBox.isChecked()){
                isDeveloperMode = true;
                Log.i(SCRIBBLE_IT_LOGGER_TAG, "Developer mode on");
            }
            else {
                isDeveloperMode = false;
                Log.i(SCRIBBLE_IT_LOGGER_TAG, "Developer mode off");
            }
        });
    }

    public void updateLoginStatus(){
        loginStatus.setText(loginService.getStatus());
    }
}
