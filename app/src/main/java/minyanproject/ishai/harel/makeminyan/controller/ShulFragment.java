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

import minyanproject.ishai.harel.makeminyan.R;

public class ShulFragment extends Fragment {

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.shul_layout,container,false);
        return myView;
    }
}
