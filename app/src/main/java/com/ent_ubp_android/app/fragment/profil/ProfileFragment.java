package com.ent_ubp_android.app.fragment.profil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.ent_ubp_android.app.R;
import com.ent_ubp_android.app.adapter.recyclerview.RecyclerViewAgendaAdapter;
import com.ent_ubp_android.app.fragment.FragmentSwitcher;
import com.ent_ubp_android.app.model.Agenda;
import com.ent_ubp_android.app.model.teacher.*;

import java.text.SimpleDateFormat;
import java.util.*;

//TODO: Faire quelquechose pour les heure faites et à faire (calculer à l'aide des cours)
public class ProfileFragment extends Fragment {

    private List<Agenda> listAgenda;
    private List<Agenda> printedListAgenda;

    private int nbHourDone = 150;
    private int nbHourPrev = 196;
    private int nbHourMust = nbHourPrev-nbHourDone;


    //Teacher professeur;

    RecyclerView recyclerView;
    TextView textHourDone;
    TextView textHourPrev;
    TextView textHourSous;
    ProgressBar progressBar;

    Button bouton_contact;

    /* Fin test Données */


    public ProfileFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        remplirAgenda();
        createPrintedList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.profile_recyclerView_agenda);
        textHourDone = (TextView) rootView.findViewById(R.id.textHourDone);
        textHourPrev = (TextView) rootView.findViewById(R.id.textHourPrev);
        textHourSous = (TextView) rootView.findViewById(R.id.textHourSous);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        bouton_contact = (Button) rootView.findViewById(R.id.button_contact);

        bouton_contact.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                displayContact();
            }
        });

        return rootView;
    }

    //Do Process
    @Override
    public void onActivityCreated (Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        textHourDone.setText(Integer.toString(nbHourDone));
        textHourPrev.setText(Integer.toString(nbHourPrev));

        if(nbHourDone < nbHourPrev){
            textHourSous.setText(Integer.toString(nbHourMust));
            progressBar.setMax(nbHourPrev);
            progressBar.setProgress(nbHourDone);
        } else {
            textHourDone.setText(Integer.toString(0));
            progressBar.setMax(0);
            progressBar.setProgress(100);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(FragmentSwitcher.getActivity()));
        recyclerView.setAdapter(new RecyclerViewAgendaAdapter(printedListAgenda));
    }

    @Override
    public void onResume (){
        super.onResume();
    }

    @Override
    public void onPause (){
        super.onPause();
    }

    @Override
    public void onStop (){
        super.onStop();
    }

    @Override
    public void onViewStateRestored (Bundle savedInstanceState){
        super.onViewStateRestored(savedInstanceState);
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


    private void displayContact(){
        Fragment fragment = new ContactFragment();
    }
}