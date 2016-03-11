package com.ent_ubp_android.app.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.ent_ubp_android.app.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class ProfileFragment extends Fragment {


    /* Test Données */
    private String nomCours[] = {"Base de données", "Complexite",
            "Processus Stochastique et Meta-heuristique", "Base de données",
            "Complexite", "Processus Stochastique et Meta-heuristique"};

    private String heureCours[] = {"8:00 - 10:00", "10:15 - 12:15", "8:00 - 10:00", "10:15 - 12:15", "14:00 - 16:00", "10:15 - 12:15"};

    private String salleCours[] = {"3002", "3002", "3002", "3002", "3002", "3002"};

    private Calendar dateC[] = { new GregorianCalendar(2016, 1, 29),  new GregorianCalendar(2016, 1, 29)
            , new GregorianCalendar(2016, 2, 1),  new GregorianCalendar(2016, 2, 1)
            , new GregorianCalendar(2016, 2, 1), new GregorianCalendar(2016, 2,2)};


    private int nbHourDone = 150;
    private int nbHourPrev = 300;
    private int nbHourMust = nbHourPrev-nbHourDone;

    private Calendar dateSys ;
    /* Fin test Données */


    public ProfileFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);


        dateSys = new GregorianCalendar();

        LinearLayout main_layout = (LinearLayout) rootView.findViewById(R.id.profil_scroll_layout);
        LayoutInflater li = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        SimpleDateFormat formater = new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH);
        TextView tempDate = (TextView) li.inflate(R.layout.agenda_date, main_layout, false);
        tempDate.setText(formater.format(dateSys.getTime()));

        main_layout.addView(tempDate);


        for(int i = 0; i < nomCours.length; i++) {
           if (formater.format(dateC[i].getTime()).compareTo((formater.format(dateSys.getTime()))) == 0) {

                View tempContent = li.inflate(R.layout.agenda_content, main_layout, false);
                TextView hour = (TextView) tempContent.findViewById(R.id.agenda_hour);
                hour.setText(heureCours[i]);

                TextView nameCours = (TextView) tempContent.findViewById(R.id.agenda_cours);
                nameCours.setText(nomCours[i]);

                TextView place = (TextView) tempContent.findViewById(R.id.agenda_place);
                place.setText(salleCours[i]);

                main_layout.addView(tempContent);

           }
        }



        ((TextView)rootView.findViewById(R.id.textHourDone)).setText(Integer.toString(nbHourDone));
        ((TextView)rootView.findViewById(R.id.textHourPrev)).setText(Integer.toString(nbHourPrev));

        if(nbHourDone < nbHourPrev){
            ((TextView)rootView.findViewById(R.id.textHourSous)).setText(Integer.toString(nbHourMust));
            ((ProgressBar) rootView.findViewById(R.id.progressBar)).setMax(nbHourPrev);
            ((ProgressBar) rootView.findViewById(R.id.progressBar)).setProgress(nbHourDone);
        } else {
            ((TextView)rootView.findViewById(R.id.textHourDone)).setText(Integer.toString(0));
            ((ProgressBar) rootView.findViewById(R.id.progressBar)).setMax(0);
            ((ProgressBar) rootView.findViewById(R.id.progressBar)).setProgress(100);
        }





        return rootView;
    }

}
