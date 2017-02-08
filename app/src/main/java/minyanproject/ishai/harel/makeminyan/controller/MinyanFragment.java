package minyanproject.ishai.harel.makeminyan.controller;

/**
 * Created by Harel on 04/02/2017.
 */
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import minyanproject.ishai.harel.makeminyan.R;

public class MinyanFragment extends Fragment {

    View myView;

    EditText etShlID;
    Spinner spNussach;
    CheckBox cbSachrit;
    CheckBox cbMincha;
    CheckBox cbArvit;

    CheckBox cbED;
    CheckBox cbSUN;
    CheckBox cbMUN;
    CheckBox cbTUE;
    CheckBox cbWEN;
    CheckBox cbTHU;
    CheckBox cbFRI;
    CheckBox cbSAT;

    LinearLayout llDaysSrt;
    LinearLayout llDaysMin;
    LinearLayout llDaysArv;

    EditText etSaHour;
    EditText etSaMinute;
    EditText etMiHour;
    EditText etMiMinute;
    EditText etArHour;
    EditText etArMinute;

    Button btnAddShl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.minyan_layout,container,false);
        findViews(myView);
        return myView;
    }

    private void findViews(View myView)
    {
        try {

            etShlID = (EditText) myView.findViewById(R.id.etShulID);
            etSaHour = (EditText) myView.findViewById(R.id.etSaHour);
            etSaMinute = (EditText) myView.findViewById(R.id.etSaMinute);
            etMiHour = (EditText) myView.findViewById(R.id.etMiHour);
            etMiMinute = (EditText) myView.findViewById(R.id.etMiMinute);
            etArHour = (EditText) myView.findViewById(R.id.etArHour);
            etArMinute = (EditText) myView.findViewById(R.id.etArMinute);

            cbSachrit = (CheckBox) myView.findViewById(R.id.cbShachrit);
            cbMincha = (CheckBox) myView.findViewById(R.id.cbMincha);
            cbArvit = (CheckBox) myView.findViewById(R.id.cbArvit);

            cbED = (CheckBox) myView.findViewById(R.id.cbED);
            cbSUN = (CheckBox) myView.findViewById(R.id.cbSUN);
            cbMUN = (CheckBox) myView.findViewById(R.id.cbMUN);
            cbTUE = (CheckBox) myView.findViewById(R.id.cbTUE);
            cbWEN = (CheckBox) myView.findViewById(R.id.cbWEN);
            cbTHU = (CheckBox) myView.findViewById(R.id.cbTHU);
            cbFRI = (CheckBox) myView.findViewById(R.id.cbFRI);
            cbSAT = (CheckBox) myView.findViewById(R.id.cbSAT);

            llDaysSrt = (LinearLayout) myView.findViewById(R.id.llDaysSrt);
            llDaysMin = (LinearLayout) myView.findViewById(R.id.llDaysMin);
            llDaysArv = (LinearLayout) myView.findViewById(R.id.llDaysArv);

            btnAddShl = (Button) myView.findViewById(R.id.btnAddShl);

            initCb();
        }
        catch (Exception e) {

        }

    }

    private void initCb()
    {
        cbED.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    cbSUN.setChecked(true);
                    cbMUN.setChecked(true);
                    cbTUE.setChecked(true);
                    cbWEN.setChecked(true);
                    cbTHU.setChecked(true);
                    cbFRI.setChecked(true);
                    cbSAT.setChecked(true);
                }
                else {
                    cbSUN.setChecked(false);
                    cbMUN.setChecked(false);
                    cbTUE.setChecked(false);
                    cbWEN.setChecked(false);
                    cbTHU.setChecked(false);
                    cbFRI.setChecked(false);
                    cbSAT.setChecked(false);
                }

            }
        });

        cbSachrit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                   llDaysSrt.setVisibility(View.VISIBLE);
                }
                else {
                    llDaysSrt.setVisibility(View.GONE);
                }

            }
        });

        cbMincha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    llDaysMin.setVisibility(View.VISIBLE);
                }
                else {
                    llDaysMin.setVisibility(View.GONE);
                }

            }
        });

        cbArvit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    llDaysArv.setVisibility(View.VISIBLE);

                }
                else {
                    llDaysArv.setVisibility(View.GONE);
                }

            }
        });
    }
}
