package com.ent_ubp_android.app.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ent_ubp_android.app.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfesseurFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfesseurFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfesseurFragment extends Fragment {

    public ProfesseurFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

    View rootView = inflater.inflate(R.layout.fragment_professeur, container, false);


    return rootView;



}
