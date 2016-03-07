package com.ent_ubp_android.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ent_ubp_android.app.Interface.IRecyclerViewClickListener;
import com.ent_ubp_android.app.R;
import com.ent_ubp_android.app.model.Formation;

import java.util.ArrayList;
import java.util.List;

public class FormationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private static IRecyclerViewClickListener mListener;
    private List<Formation> listFormation;
    private List<List<Formation>> listOfFormations;

    //Inner Class wrap views in the list
    public static class FormationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView nom;

        public FormationViewHolder(View itemView) {
            super(itemView);

            nom = (TextView) itemView.findViewById(R.id.popup_nom_formation);
            itemView.setOnClickListener(this);
        }

        public void bind(Formation formation) {
            nom.setText(formation.getNom());
        }

        @Override
        public void onClick(View v) {
            mListener.onRecyclerViewItemClicked(v, this.getLayoutPosition());
        }
    }

    //Constructor of the Adapter
    public FormationAdapter(Context context, IRecyclerViewClickListener itemListener, List<Formation> listFormation){
        this.listFormation = listFormation;
        this.context = context;
        this.mListener = itemListener;
        listOfFormations = new ArrayList<List<Formation>>();
        listOfFormations.add(this.listFormation);
    }

    //When the adapter is created
    @Override
    public FormationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popup_cell_card, parent, false);
        FormationViewHolder viewHolder = new FormationViewHolder(view);

        return viewHolder;
    }

    //Make a link between views and informations
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Formation formation = listFormation.get(position);
        ((FormationViewHolder)holder).bind(formation);
    }

    //Number of items in the list
    @Override
    public int getItemCount() {
        return listFormation.size();
    }

    public void changeDataSet(List<Formation> list){
        listOfFormations.add(list);
        listFormation = list;
        notifyDataSetChanged();
    }
}
