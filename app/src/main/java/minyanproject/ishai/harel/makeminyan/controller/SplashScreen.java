package minyanproject.ishai.harel.makeminyan.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import minyanproject.ishai.harel.makeminyan.R;

public class SplashScreen extends AppCompatActivity {

    EditText etWelc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                etWelc = (EditText)findViewById(R.id.etWelc);

                SharedPreferences sharedPreferences = getSharedPreferences("userinfo", MODE_PRIVATE);
                String userNm = "";
                if (sharedPreferences.contains("username")) {
                    userNm = sharedPreferences.getString("username", null);
                    changeText(userNm);
                }
                /* Create an Intent that will start the Menu-Activity. */
                if(userNm.isEmpty())
                    startMainActivity();
                else
                    startNavigationController();
                SplashScreen.this.finish();
            }
        }, 1500);
    }


    private void changeText(String user)
    {
        etWelc.setText(etWelc.getText().toString() + user);
        Toast.makeText(this, "load name", Toast.LENGTH_SHORT).show();
    }

    private void startNavigationController(){
        Intent myIntent = new Intent(this, LoginNavigationController.class);
        startActivity(myIntent);
    }

    private void startMainActivity(){
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }
}
