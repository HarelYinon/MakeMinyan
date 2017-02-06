package minyanproject.ishai.harel.makeminyan.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private EditText etUserName;
    private EditText etUserPassword;
    private Button btnLoginButton;
    private CheckBox cbShowPass;
    private  Button btnRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
    }

    private void findViews(){
        etUserName = (EditText)findViewById(R.id.etUserName);
        etUserPassword = (EditText)findViewById(R.id.etUserPassword);
        btnLoginButton = (Button) findViewById(R.id.btnLoginButton);
        btnRegisterButton = (Button) findViewById(R.id.btnRegisterButton);
        cbShowPass = ( CheckBox ) findViewById( R.id.cbShowPass);

        btnLoginButton.setOnClickListener(this);
        btnRegisterButton.setOnClickListener(this);

        final TransformationMethod passwordTransformationMethod = new PasswordTransformationMethod();
        cbShowPass.setOnCheckedChangeListener(new OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    etUserPassword.setTransformationMethod(null);
                }
                else {
                    etUserPassword.setTransformationMethod(passwordTransformationMethod);
                }
                etUserPassword.setSelection(etUserPassword.getText().length());
            }
        });
    }

    @Override
    public void onClick(View v) {

        CheckUserAndPass(v);
        if ( v == btnLoginButton) {
            SharedPreferences.Editor editor = getSharedPreferences("userinfo",MODE_PRIVATE).edit();
            editor.putString("etUserName",etUserName.getText().toString());
            editor.putString("password",etUserPassword.getText().toString());
            editor.commit();
            startNavigationController();
            //startActivityLogin();
        }
        else if(v == btnRegisterButton) {
            register();
        }

    }

    private void startNavigationController(){
        Intent myIntent = new Intent(this, LoginNavigationController.class);
        startActivity(myIntent);
    }


    private void register()
    {
        //TODO: implement register operation!!!!!
    }

    //TODO: this func need to check if the User exist and the password is the right password
    private boolean CheckUserAndPass(View v) {
        if(v==btnLoginButton){

        }
        else if (v==btnRegisterButton){

        }
        Toast.makeText(getApplicationContext(), R.string.InvalidUserNameOrPassword, Toast.LENGTH_SHORT).show();
        return true;
    }
}
