package com.ent_ubp_android.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ent_ubp_android.app.model.Professeur;
import java.util.ArrayList;

import com.ent_ubp_android.app.R;

/**
 * Created by SIN on 02/03/2016.
 */
public class ProfessorAdapter extends BaseAdapter {

    // Une liste de personnes
    private ArrayList<Professeur> professeurs;

    //Le contexte dans lequel est présent notre adapter
    private Context mContext;

    //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private LayoutInflater mInflater;

    public ProfessorAdapter(Context context, ArrayList<Professeur> aListP) {
        mContext = context;
        professeurs = aListP;
        mInflater = LayoutInflater.from(mContext);
    }

    public int getCount() {
        return professeurs.size();
    }

    public Object getItem(int position) {
        return professeurs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LinearLayout layoutItem;
        //(1) : Réutilisation des layouts
        if (convertView == null) {
            //Initialisation de notre item à partir du  layout XML "personne_layout.xml"
            layoutItem = (LinearLayout) mInflater.inflate(R.layout.professeur_item, parent, false);
        } else {
            layoutItem = (LinearLayout) convertView;
        }

        //(2) : Récupération des TextView de notre layout
        TextView tv_Nom = (TextView)layoutItem.findViewById(R.id.prof_Nom);
        TextView tv_Prenom = (TextView)layoutItem.findViewById(R.id.prof_Prenom);

        //(3) : Renseignement des valeurs
        tv_Nom.setText(professeurs.get(position).getNom());
        tv_Prenom.setText(professeurs.get(position).getPrenom());


        //On retourne l'item créé.
        return layoutItem;
    }


}
