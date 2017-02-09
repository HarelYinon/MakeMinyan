package minyanproject.ishai.harel.makeminyan.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
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
import minyanproject.ishai.harel.makeminyan.model.datasource.PHPDataBaseHelper;
import minyanproject.ishai.harel.makeminyan.model.entities.User;

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


            if (v == btnLoginButton) {
                AsyncTaskSignIn();
            } else if (v == btnRegisterButton) {
                aSyncTaskSignUp();
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

    private void aSyncTaskSignUp()
    {
        User localUser = new User(etUserName.getText().toString(),etUserPassword.getText().toString());
        User email = new User("Email",null);

        if(localUser.getUsername().isEmpty())
            ;
        else if(localUser.getPassword().isEmpty())
            ;
        else if(email.getUsername().isEmpty())
            ;
        else
        {
            new AsyncTask<User, Void, Boolean>() {
                @Override
                protected Boolean doInBackground(User... params) {
                    PHPDataBaseHelper phpDataBaseHelper = new PHPDataBaseHelper();
                    if (phpDataBaseHelper.signup(params[0].getUsername(),
                            params[0].getPassword(),
                            params[1].getUsername()))//this email
                        return true;
                    return false;
                }

                @Override
                protected void onPostExecute(Boolean aBoolean) {
                    //TODO:replace with register action
                    if (aBoolean)
                        ;//Toast succeeded
                    else
                        ;//Toast failed
                }
            }.execute(localUser, email);
        }
    }

    private void AsyncTaskSignIn()
    {
        User localUser = new User(etUserName.getText().toString(),etUserPassword.getText().toString());

        if(localUser.getUsername().isEmpty())
            ;
        else if(localUser.getPassword().isEmpty())
            ;
        else
        {
            new AsyncTask<User, Void, Boolean>() {
                @Override
                protected Boolean doInBackground(User... params) {
                    PHPDataBaseHelper phpDataBaseHelper = new PHPDataBaseHelper();
                    if (phpDataBaseHelper.signin(params[0].getUsername(),
                            params[0].getPassword()))
                        return true;
                    return false;
                }

                @Override
                protected void onPostExecute(Boolean aBoolean) {
                    //TODO:replace with register action
                    if (aBoolean) {
                        SharedPreferences.Editor editor = getSharedPreferences("userinfo", MODE_PRIVATE).edit();
                        editor.putString("username", etUserName.getText().toString());
                        editor.putString("password", etUserPassword.getText().toString());
                        editor.commit();
                        startNavigationController();
                        finish();
                    }

                    else
                    {
                        Toast.makeText(getApplicationContext(),"could'nt sign in",Toast.LENGTH_LONG);
                    }
                }
            }.execute(localUser);
        }
    }
}
