package com.ent_ubp_android.app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ent_ubp_android.app.R;
import com.ent_ubp_android.app.adapter.AgendaAdapter;
import com.ent_ubp_android.app.model.Agenda;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class AgendaFragment extends Fragment {

    private List<Agenda> agenda;

    RecyclerView recyclerView;

    public AgendaFragment() {
    }

    //Instantiate Object
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        remplirAgenda();
    }

    //Instantiate View Object
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Get the fragment
        View agenda_view = inflater.inflate(R.layout.fragment_agenda, container, false);

        recyclerView = (RecyclerView) agenda_view.findViewById(R.id.agenda_recyclerView);

        return agenda_view;
    }

    //Do Process
    @Override
    public void onActivityCreated (Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        //Définition de l'agencement des cellules
        recyclerView.setLayoutManager(new LinearLayoutManager(FragmentSwitcher.getActivity()));
        //Permet de remplir notre recyclerView
        recyclerView.setAdapter(new AgendaAdapter(agenda));
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

    private void remplirAgenda(){
        agenda = new ArrayList<Agenda>();
        agenda.add(new Agenda(new GregorianCalendar(2016, 1, 29), "8:00 - 10:00", "Compilation", "3002"));
        agenda.add(new Agenda(new GregorianCalendar(2016, 1, 29), "8:00 - 10:00", "Compilation", "3002"));
        agenda.add(new Agenda(new GregorianCalendar(2016, 2, 1), "8:00 - 10:00", "Compilation", "3002"));
        agenda.add(new Agenda(new GregorianCalendar(2016, 2, 1), "8:00 - 10:00", "Compilation", "3002"));
        agenda.add(new Agenda(new GregorianCalendar(2016, 2, 2), "8:00 - 10:00", "Compilation", "3002"));
        agenda.add(new Agenda(new GregorianCalendar(2016, 2, 2), "8:00 - 10:00", "Compilation", "3002"));
        agenda.add(new Agenda(new GregorianCalendar(2016, 2, 2), "8:00 - 10:00", "Compilation", "3002"));
        agenda.add(new Agenda(new GregorianCalendar(2016, 2, 2), "8:00 - 10:00", "Compilation", "3002"));
    }
}
