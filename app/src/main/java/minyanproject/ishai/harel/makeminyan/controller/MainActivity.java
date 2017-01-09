package minyanproject.ishai.harel.makeminyan.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

import minyanproject.ishai.harel.makeminyan.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText userName;
    private EditText userPassword;
    private Button loginButton;
    private CheckBox showPass;
    private  Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
    }

    private void findViews(){
        userName = (EditText)findViewById(R.id.userName);
        userPassword = (EditText)findViewById(R.id.userPassword);
        loginButton = (Button) findViewById(R.id.loginButton);
        registerButton = (Button) findViewById(R.id.registerButton);
        showPass = ( CheckBox ) findViewById( R.id.showPass);

        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);

        final TransformationMethod passwordTransformationMethod = new PasswordTransformationMethod();
        showPass.setOnCheckedChangeListener(new OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    userPassword.setTransformationMethod(null);
                }
                else {
                    userPassword.setTransformationMethod(passwordTransformationMethod);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if ( v == loginButton && CheckUserAndPass()) {
            //TODO: create the next Activity for the cond above
        }
        else{
            Toast.makeText(getApplicationContext(), R.string.InvalidUserNameOrPassword, Toast.LENGTH_SHORT).show();
        }
    }

    //TODO: this func need to check if the User exist and the password is the right password
    private boolean CheckUserAndPass() {
        return false;
    }
}
