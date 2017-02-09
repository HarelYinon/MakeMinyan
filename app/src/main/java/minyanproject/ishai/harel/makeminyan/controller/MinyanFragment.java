package minyanproject.ishai.harel.makeminyan.controller;

/**
 * Created by Harel on 04/02/2017.
 */
import android.app.Fragment;
import android.app.TimePickerDialog;
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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Timer;

import minyanproject.ishai.harel.makeminyan.R;

public class MinyanFragment extends Fragment {

    View myView;

    EditText etShlID;
    Spinner spNussach;
    CheckBox cbSachrit;
    CheckBox cbMincha;
    CheckBox cbArvit;

    CheckBox cbED;
    ToggleButton tbSUN;
    ToggleButton tbMON;
    ToggleButton tbTUE;
    ToggleButton tbWEN;
    ToggleButton tbTHU;
    ToggleButton tbFRI;
    ToggleButton tbSAT;

    LinearLayout llDaysSrt;
    LinearLayout llDaysMin;
    LinearLayout llDaysArv;

    Button btnSaTime;
    Button btnMiTime;
    Button btnArTime;
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

            cbSachrit = (CheckBox) myView.findViewById(R.id.cbShachrit);
            cbMincha = (CheckBox) myView.findViewById(R.id.cbMincha);
            cbArvit = (CheckBox) myView.findViewById(R.id.cbArvit);

            cbED = (CheckBox) myView.findViewById(R.id.cbED);
            tbSUN = (ToggleButton) myView.findViewById(R.id.tbSUN);
            tbMON = (ToggleButton) myView.findViewById(R.id.tbMON);
            tbTUE = (ToggleButton) myView.findViewById(R.id.tbTUE);
            tbWEN = (ToggleButton) myView.findViewById(R.id.tbWEN);
            tbTHU = (ToggleButton) myView.findViewById(R.id.tbTHU);
            tbFRI = (ToggleButton) myView.findViewById(R.id.tbFRI);
            tbSAT = (ToggleButton) myView.findViewById(R.id.tbSAT);

            llDaysSrt = (LinearLayout) myView.findViewById(R.id.llDaysSrt);
            llDaysMin = (LinearLayout) myView.findViewById(R.id.llDaysMin);
            llDaysArv = (LinearLayout) myView.findViewById(R.id.llDaysArv);

            btnArTime = (Button) myView.findViewById(R.id.btnArTime);
            btnMiTime = (Button) myView.findViewById(R.id.btnMiTime);
            btnSaTime = (Button) myView.findViewById(R.id.btnSaTime);

            btnAddShl = (Button) myView.findViewById(R.id.btnAddShl);

            initViews();
        }
        catch (Exception e) {

        }

    }

    private void initViews()
    {
        cbED.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    tbSUN.setChecked(true);
                    tbMON.setChecked(true);
                    tbTUE.setChecked(true);
                    tbWEN.setChecked(true);
                    tbTHU.setChecked(true);
                    tbFRI.setChecked(true);
                    tbSAT.setChecked(true);
                }
                else {
                    tbSUN.setChecked(false);
                    tbMON.setChecked(false);
                    tbTUE.setChecked(false);
                    tbWEN.setChecked(false);
                    tbTHU.setChecked(false);
                    tbFRI.setChecked(false);
                    tbSAT.setChecked(false);
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

        btnSaTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickTime(btnSaTime);
            }
        });

        btnMiTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickTime(btnMiTime);
            }
        });

        btnArTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickTime(btnArTime);
            }
        });

        btnAddShl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addMinyan();
            }
        });
    }

    private void pickTime(final Button btn)
    {
        new TimePickerDialog(getActivity(), (new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String text = hourOfDay + ":" + minute;
                btn.setText(text);
            }
        }),0,0,true).show();
    }

    private void addMinyan()
    {

    }
}
