package com.ent_ubp_android.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ent_ubp_android.app.R;
import com.ent_ubp_android.app.model.Agenda;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class RecyclerViewAgendaAdapter extends RecyclerView.Adapter<RecyclerViewAgendaAdapter.AgendaViewHolder> {

    private List<Agenda> listAgenda;

    public static class AgendaViewHolder extends RecyclerView.ViewHolder{
        public TextView day;
        public TextView heure;
        public TextView cours;
        public TextView salle;

        public AgendaViewHolder(View itemView) {
            super(itemView);

            day = (TextView)  itemView.findViewById(R.id.agenda_day);
            heure = (TextView) itemView.findViewById(R.id.agenda_hour);
            cours = (TextView) itemView.findViewById(R.id.agenda_cours);
            salle = (TextView) itemView.findViewById(R.id.agenda_salle);
        }

        public void bind(Agenda agenda, int visibilty){
            DateFormat formatter = new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH);
            day.setText(formatter.format(agenda.getJours().getTime()));
            day.setVisibility(visibilty);
            heure.setText(agenda.getHeure());
            cours.setText(agenda.getCours());
            salle.setText(agenda.getSalle());
        }
    }

    public RecyclerViewAgendaAdapter(List<Agenda> list){
        this.listAgenda = list;
    }

    @Override
    public AgendaViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.agenda_cell_card, viewGroup, false);
        return new AgendaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AgendaViewHolder agendaViewHolder, int position) {
        Agenda agenda = listAgenda.get(position);
        int visibility = setVisibilityDate(position);
        agendaViewHolder.bind(agenda, visibility);
    }

    @Override
    public int getItemCount() {
        return listAgenda.size();
    }

    private int setVisibilityDate(int position){
        if(position == 0)
            return View.VISIBLE;

        Calendar newDay = listAgenda.get(position).getJours();
        Calendar oldDay = listAgenda.get(position-1).getJours();

        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH);
        String newDayS = formatter.format(newDay.getTime());
        String oldDayS = formatter.format(oldDay.getTime());

        if(!newDayS.equals(oldDayS))
            return View.VISIBLE;

        return View.GONE;
    }

}
