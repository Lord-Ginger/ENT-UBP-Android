package com.ent_ubp_android.app.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListAdapter;

import com.ent_ubp_android.app.R;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    String[] cour = new String[]{"Histoire","Géographie","Mathématique","Français","Anglais"};
    String[] salle = new String[]{"123er132","dr312er","az1254ae","hjkhjk123","arb1235"};
    String[] description = new String[]{"(1)ejzh eoihezavhiazg ehgazgazpgizaog oijrgairg",
                                        "(2)dr312er brlgehrge fagrohruig oiuarghrg",
                                        "(3)az1254ae  uagihpzrguhrzgaurg guiahrg uaygir",
                                        "(4)hjkhjk123argmaernjgrn   l aigurg auhriupga",
                                        "(5)arb1235akrgjka iubgargaripgurg auihrpihgpauhrg"};

    public ProfileFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        //TODO:créer l'agenda



        //TODO:Faire la progressBar (heure_prévu - heure_fais)





        return rootView;
    }

}
