package com.ent_ubp_android.app.adapter.recyclerview;


import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ent_ubp_android.app.Interface.IRecyclerViewClickListener;
import com.ent_ubp_android.app.R;
import com.ent_ubp_android.app.model.teacher.*;

import java.util.List;

public class RecyclerViewProfesseurAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static IRecyclerViewClickListener mListener;
    private List<Teacher> listTeacher;

    private class ViewHolderProfesseur extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nom;
        TextView prenom;
        TextView type;
        TextView mail;
        TextView tel;

        public ViewHolderProfesseur(View itemView) {
            super(itemView);

            nom = (TextView) itemView.findViewById(R.id.professeur_nom);
            prenom = (TextView) itemView.findViewById(R.id.professeur_prenom);
            type = (TextView) itemView.findViewById(R.id.professeur_type);
            mail = (TextView) itemView.findViewById(R.id.professeur_mail);
            tel = (TextView) itemView.findViewById(R.id.professeur_tel);

            itemView.setOnClickListener(this);
        }

        public void bind(Teacher teacher) {
            nom.setText(teacher.getName().getLastName());
            prenom.setText(teacher.getName().getFirstName());
            type.setText(teacher.getType().name());
            mail.setText(teacher.getContact().getPhones().toString());
            tel.setText(teacher.getContact().getEmails().toString());

        }

        @Override
        public void onClick(View v) {
            mListener.onRecyclerViewItemClicked(v, getLayoutPosition());
        }
    }

    public RecyclerViewProfesseurAdapter(IRecyclerViewClickListener listener, List<Teacher> listTeacher){
        this.listTeacher = listTeacher;
        this.mListener = listener;
    }

    @Override
    public ViewHolderProfesseur onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.professeur_display_cell_card, parent, false);
        return new ViewHolderProfesseur(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolderProfesseur) holder).bind(listTeacher.get(position));
    }

    @Override
    public int getItemCount() {
        return listTeacher.size();
    }
}
