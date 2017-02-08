package minyanproject.ishai.harel.makeminyan.controller;

/**
 * Created by Harel on 04/02/2017.
 */
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import minyanproject.ishai.harel.makeminyan.R;

public class ShulFragment extends Fragment {

    View myView;

    EditText etShulname;
    EditText etEmail;
    EditText etTelephone;
    EditText etWebsite;
    EditText etCity;
    EditText etStreet;
    EditText etNumber;
    EditText etDescription;

    Button btnAddShl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.shul_layout,container,false);
        return myView;
    }

    private void findViews(View myView)
    {
        etShulname = (EditText) myView.findViewById(R.id.etShulname);
        etEmail = (EditText) myView.findViewById(R.id.etEmail);
        etTelephone = (EditText) myView.findViewById(R.id.etTelephone);
        etWebsite = (EditText) myView.findViewById(R.id.etWebsite);
        etCity = (EditText) myView.findViewById(R.id.etCity);
        etStreet = (EditText) myView.findViewById(R.id.etStreet);
        etNumber = (EditText) myView.findViewById(R.id.etNumber);
        etDescription = (EditText) myView.findViewById(R.id.etDescription);

        btnAddShl = (Button) myView.findViewById(R.id.btnAddShl);


    }

    private void initViews()
    {
        btnAddShl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etShulname.getText().toString().isEmpty() ||
                        etEmail.getText().toString().isEmpty() ||
                        etTelephone.getText().toString().isEmpty() ||
                        etWebsite.getText().toString().isEmpty() ||
                        etCity.getText().toString().isEmpty() ||
                        etStreet.getText().toString().isEmpty() ||
                        etNumber.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), R.string.EmptyField, Toast.LENGTH_SHORT).show();
                }
                else
                {}
            }
        });
    }

    private boolean isEmailValid(String email) {
        return email.contains("@") && (email.contains(".com") || email.contains(".co.il"));
    }


}
