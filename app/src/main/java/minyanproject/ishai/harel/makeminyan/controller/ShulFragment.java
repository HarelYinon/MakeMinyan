package minyanproject.ishai.harel.makeminyan.controller;

/**
 * Created by Harel on 04/02/2017.
 */
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import minyanproject.ishai.harel.makeminyan.R;
import minyanproject.ishai.harel.makeminyan.model.datasource.PHPDataBaseHelper;
import minyanproject.ishai.harel.makeminyan.model.entities.Shul;

import static android.content.Context.MODE_PRIVATE;

public class ShulFragment extends Fragment {

    View myView;

    EditText etShulname;
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
        etTelephone = (EditText) myView.findViewById(R.id.etTelephone);
        etWebsite = (EditText) myView.findViewById(R.id.etWebsite);
        etCity = (EditText) myView.findViewById(R.id.etCity);
        etStreet = (EditText) myView.findViewById(R.id.etStreet);
        etNumber = (EditText) myView.findViewById(R.id.etNumber);
        etDescription = (EditText) myView.findViewById(R.id.etDescription);

        btnAddShl = (Button) myView.findViewById(R.id.btnAddShl);

        initViews();
    }

    private void initViews()
    {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userinfo",MODE_PRIVATE);
        final String userName = sharedPreferences.getString("username","Anonymous");

        btnAddShl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etShulname.getText().toString().isEmpty() ||
                        etTelephone.getText().toString().isEmpty() ||
                        etWebsite.getText().toString().isEmpty() ||
                        etCity.getText().toString().isEmpty() ||
                        etStreet.getText().toString().isEmpty() ||
                        etNumber.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), R.string.EmptyField, Toast.LENGTH_SHORT).show();
                }
                else
                {

                    new AsyncTask<Shul, Void, Boolean>() {
                        @Override
                        protected Boolean doInBackground(Shul... params) {
                            PHPDataBaseHelper phpDataBaseHelper = new PHPDataBaseHelper();
                            return (phpDataBaseHelper.addShul(params[0]));
                        }

                        @Override
                        protected void onPostExecute(Boolean aBoolean) {
                            //TODO:replace with register action
                            if (aBoolean) {
                                Toast.makeText(getActivity(),"Shul has been added!",Toast.LENGTH_LONG);
                            }

                            else
                            {
                                Toast.makeText(getActivity(),"could'nt sign in",Toast.LENGTH_LONG);
                            }
                        }
                    }.execute(new Shul(etShulname.getText().toString()
                            ,etCity.getText().toString()+etStreet.getText().toString()+etNumber.getText().toString(),
                            etDescription.getText().toString(),
                            userName, etWebsite.getText().toString(),
                            etTelephone.getText().toString()
                            ));
                }
            }
        });
    }



}
