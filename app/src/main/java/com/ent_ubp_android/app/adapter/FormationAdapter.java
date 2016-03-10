package com.ent_ubp_android.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ent_ubp_android.app.Interface.IRecyclerViewClickListener;
import com.ent_ubp_android.app.R;
import com.ent_ubp_android.app.model.formation.FormationComponent;
import com.ent_ubp_android.app.model.formation.FormationComposite;
import com.ent_ubp_android.app.model.formation.FormationEnum;
import com.ent_ubp_android.app.model.formation.FormationLeaf;

import java.util.List;

public class FormationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static IRecyclerViewClickListener mListener;
    private List<FormationComponent> listFormation;

    //Inner Class wrap views in the list
    public static class FormationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView nom;
        public FormationEnum type;

        public FormationViewHolder(View itemView) {
            super(itemView);

            nom = (TextView) itemView.findViewById(R.id.formation_nom);
            itemView.setOnClickListener(this);
        }

        public void bindLeaf(FormationLeaf formation) {
            nom.setText(formation.getName());
            type = FormationEnum.LEAF;
        }
        public void bindComposite(FormationComposite formation) {
            nom.setText(formation.getName());
            type = FormationEnum.COMPOSITE;
        }

        @Override
        public void onClick(View v) {
            mListener.onRecyclerViewItemClicked(v, this.getLayoutPosition(), type);
        }
    }

    //Constructor of the Adapter
    public FormationAdapter(IRecyclerViewClickListener itemListener, List<FormationComposite> listFormation){
        this.listFormation = (List<FormationComponent>) (List<?>) listFormation;
        this.mListener = itemListener;
    }

    //When the adapter is created
    @Override
    public FormationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.formation_cell_card, parent, false);

        return new FormationViewHolder(view);
    }

    //Make a link between views and informations
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FormationComponent formation = listFormation.get(position);
        if(formation.isLeaf())
            ((FormationViewHolder)holder).bindLeaf((FormationLeaf) formation);
        else
            ((FormationViewHolder)holder).bindComposite((FormationComposite) formation);
    }

    //Number of items in the list
    @Override
    public int getItemCount() {
        return listFormation == null ? 0 : listFormation.size();
    }

    public void changeDataSet(List<FormationComponent> list){
        listFormation.clear();
        listFormation.addAll(list);
        notifyDataSetChanged();
    }

    public FormationComposite getItem(int position){
        return (FormationComposite) listFormation.get(position);
    }
}
