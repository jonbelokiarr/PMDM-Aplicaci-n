package com.example.pmdm_aplicacion.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pmdm_aplicacion.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompartirFragment extends Fragment {

    public CompartirFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compartir, container, false);
    }
}
