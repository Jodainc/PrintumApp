package com.kotan.printum.Ui.Fragments;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kotan.printum.R;

/**
 * Created by Kotan@JoyDainc on 05/12/2016.
 */

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Certificate extends Fragment {

    public Certificate() {
        // Required empty public constructor
    }

    public static Certificate newInstance(/*parámetros*/) {
        Certificate fragment = new Certificate();
        // Setup parámetros
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Gets parámetros
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View root = inflater.inflate(R.layout.fragment_leads, container, false);

        return null;
    }
}
