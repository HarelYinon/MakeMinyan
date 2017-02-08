package minyanproject.ishai.harel.makeminyan.controller;

/**
 * Created by Harel on 04/02/2017.
 */
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import minyanproject.ishai.harel.makeminyan.R;

public class ProfileFragment extends Fragment {

    View myView;

    EditText etUserName;
    EditText etNewPass;
    EditText etVerifyNewPass;

    CheckBox cbShowPass1;
    CheckBox cbShowPass2;

    Button btnUpdateProfile;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.profile_layout,container,false);
        findViews(myView);
        return myView;
    }

    private void findViews(View myView)
    {
        etUserName = (EditText) myView.findViewById(R.id.etUserName);
        etNewPass = (EditText) myView.findViewById(R.id.etNewPass);
        etVerifyNewPass =  (EditText) myView.findViewById(R.id.etVerifyNewPass);

        cbShowPass1 = (CheckBox)myView.findViewById(R.id.cbShowPass1);
        cbShowPass2 = (CheckBox)myView.findViewById(R.id.cbShowPass2);

        btnUpdateProfile=(Button) myView.findViewById((R.id.btnUpdateProfile));


        initViews();
    }


    private void initViews()
    {
        final TransformationMethod passwordTransformationMethod = new PasswordTransformationMethod();
        cbShowPass1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    etNewPass.setTransformationMethod(null);
                }
                else {
                    etNewPass.setTransformationMethod(passwordTransformationMethod);
                }
                etNewPass.setSelection(etNewPass.getText().length());
            }
        });

        cbShowPass2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    etVerifyNewPass.setTransformationMethod(null);
                }
                else {
                    etVerifyNewPass.setTransformationMethod(passwordTransformationMethod);
                }
                etVerifyNewPass.setSelection(etVerifyNewPass.getText().length());
            }
        });

    }
}
