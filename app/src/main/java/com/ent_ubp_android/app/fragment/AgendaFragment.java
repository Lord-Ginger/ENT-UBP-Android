package com.ent_ubp_android.app.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.ent_ubp_android.app.R;
import com.ent_ubp_android.app.adapter.AgendaAdapter;
import com.ent_ubp_android.app.model.Agenda;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class AgendaFragment extends Fragment {
    private List<Agenda> agenda = new ArrayList<Agenda>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Get the fragment
        View agenda_view = inflater.inflate(R.layout.fragment_agenda, container, false);

        remplirAgenda();
        RecyclerView recyclerView = (RecyclerView) agenda_view.findViewById(R.id.agenda_recyclerView);
        //Définition de l'agencement des cellules
        recyclerView.setLayoutManager(new LinearLayoutManager(agenda_view.getContext()));
        //Permet de remplir notre recyclerView
        recyclerView.setAdapter(new AgendaAdapter(agenda));

        return agenda_view;
    }

    private void remplirAgenda(){
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
