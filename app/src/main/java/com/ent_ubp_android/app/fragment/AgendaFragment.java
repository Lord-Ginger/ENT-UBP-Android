package com.ent_ubp_android.app.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ent_ubp_android.app.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class AgendaFragment extends Fragment {

    /* Test Données */
    private String nomCours[] = {"Base de données", "Complexite",
            "Processus Stochastique et Meta-heuristique", "Base de données",
            "Complexite", "Processus Stochastique et Meta-heuristique"};

    private String heureCours[] = {"8:00 - 10:00", "10:15 - 12:15", "8:00 - 10:00", "10:15 - 12:15", "8:00 - 10:00", "10:15 - 12:15"};

    private String salleCours[] = {"3002", "3002", "3002", "3002", "3002", "3002"};

    private Calendar dateC[] = { new GregorianCalendar(2016, 1, 29),  new GregorianCalendar(2016, 1, 29)
            , new GregorianCalendar(2016, 2, 1),  new GregorianCalendar(2016, 2, 1)
            , new GregorianCalendar(2016, 2, 1), new GregorianCalendar(2016, 2,2)};

    private Calendar currentDate = null;
    /* Fin test Données */




    public AgendaFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Get the fragment
        View agenda_view = inflater.inflate(R.layout.fragment_agenda, container, false);

        //Get the main layout
        LinearLayout main_layout = (LinearLayout) agenda_view.findViewById(R.id.agenda_main_layout);
        LayoutInflater li = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for(int i = 0; i < nomCours.length; i++){
            if(currentDate == null || !currentDate.equals(dateC[i])){
                currentDate = dateC[i];
                SimpleDateFormat formater = new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH);

                TextView tempDate = (TextView) li.inflate(R.layout.agenda_date, main_layout, false);
                tempDate.setText(formater.format(currentDate.getTime()));

                main_layout.addView(tempDate);
            }

            View tempContent = li.inflate(R.layout.agenda_content, main_layout, false);
            TextView hour = (TextView) tempContent.findViewById(R.id.agenda_hour);
            hour.setText(heureCours[i]);

            TextView nameCours = (TextView) tempContent.findViewById(R.id.agenda_cours);
            nameCours.setText(nomCours[i]);

            TextView place = (TextView) tempContent.findViewById(R.id.agenda_place);
            place.setText(salleCours[i]);

            main_layout.addView(tempContent);
        }

        return agenda_view;
    }

}