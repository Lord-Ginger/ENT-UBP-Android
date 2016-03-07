package com.ent_ubp_android.app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.ent_ubp_android.app.R;
import com.ent_ubp_android.app.adapter.AgendaAdapter;
import com.ent_ubp_android.app.model.Agenda;

import java.text.SimpleDateFormat;
import java.util.*;

public class ProfileFragment extends Fragment {

    private List<Agenda> listAgenda;
    private List<Agenda> printedListAgenda;

    private int nbHourDone = 150;
    private int nbHourPrev = 300;
    private int nbHourMust = nbHourPrev-nbHourDone;

    /* Fin test Données */


    public ProfileFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        //Agenda
        remplirAgenda();
        createPrintedList();

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.profile_recyclerView_agenda);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        recyclerView.setAdapter(new AgendaAdapter(printedListAgenda));

        //Faire la progressBar (heure_prévu - heure_fais)
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

    private void createPrintedList(){
        printedListAgenda = new ArrayList<Agenda>();
        Calendar dateSys = new GregorianCalendar();

        SimpleDateFormat formater = new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH);
        String dateSysString = formater.format(dateSys.getTime());
        String dateAgenda;

        for(Agenda agenda : listAgenda){
            dateAgenda = formater.format(agenda.getJours().getTime());
            if(dateSysString.equals(dateAgenda)){
                printedListAgenda.add(agenda);
            }
        }
    }

    private void remplirAgenda(){
        listAgenda = new ArrayList<Agenda>();
        listAgenda.add(new Agenda(new GregorianCalendar(2016, 2, 6), "8:00 - 10:00", "Compilation", "3002"));
        listAgenda.add(new Agenda(new GregorianCalendar(2016, 2, 6), "8:00 - 10:00", "Compilation", "3002"));
        listAgenda.add(new Agenda(new GregorianCalendar(2016, 2, 7), "8:00 - 10:00", "Compilation", "3002"));
        listAgenda.add(new Agenda(new GregorianCalendar(2016, 2, 7), "8:00 - 10:00", "Compilation", "3002"));
        listAgenda.add(new Agenda(new GregorianCalendar(2016, 2, 7), "8:00 - 10:00", "Compilation", "3002"));
        listAgenda.add(new Agenda(new GregorianCalendar(2016, 2, 8), "8:00 - 10:00", "Compilation", "3002"));
        listAgenda.add(new Agenda(new GregorianCalendar(2016, 2, 9), "8:00 - 10:00", "Compilation", "3002"));
        listAgenda.add(new Agenda(new GregorianCalendar(2016, 2, 10), "8:00 - 10:00", "Compilation", "3002"));
    }

}
