package com.ent_ubp_android.app.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.ent_ubp_android.app.R;
import com.ent_ubp_android.app.adapter.ProfessorAdapter;
import com.ent_ubp_android.app.model.Professeur;

import java.util.ArrayList;


public class ProfesseurFragment extends Fragment {

    private ArrayList<Professeur> professeurs;
    private ArrayList<String> nomProf;

    public ProfesseurFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ListView professeurListView ;



        /*création du jeux de donnée*/

        Professeur pr1 = new Professeur("Jule","Berman",300);
        Professeur pr2 = new Professeur("Rob","Truman",300);
        Professeur pr3 = new Professeur("Bert","York",300);
        Professeur pr4 = new Professeur("Julie","Young",300);
        Professeur pr5 = new Professeur("Fred","Lardon",100);
        Professeur pr6 = new Professeur("Annick","Brad",100);


        /*---------------------------*/

        View rootView = inflater.inflate(R.layout.fragment_professeur, container, false);

       // setContentView(R.layout.main);


        /*FIXME : l'appliplante il faut voir pourquois
        * j'ai remplacer par une ArrayAdapter en attendant pour tester le style
        * --> essayer de voir en faisant une liste faites à la main ou voir comment placer des items
	* --> sinon faire une grosse scrollview comme l'agenda mais les éléments doivent êtres clicables
        * */


         /*
        professeurs.add(pr1);
        professeurs.add(pr2);
        professeurs.add(pr3);
        professeurs.add(pr4);
        professeurs.add(pr5);
        professeurs.add(pr6);
        */

        nomProf.add(pr1.getNom());
        nomProf.add(pr2.getNom());
        nomProf.add(pr3.getNom());
        nomProf.add(pr4.getNom());
        nomProf.add(pr5.getNom());
        nomProf.add(pr6.getNom());

        //ProfessorAdapter adapter = new ProfessorAdapter(rootView.getContext(), professeurs);
        ArrayAdapter adapter = new ArrayAdapter<String>(rootView.getContext(),R.layout.fragment_professeur,nomProf);

        //Récupération du composant ListView
        professeurListView = (ListView)rootView.findViewById(R.id.listView);

        //Initialisation de la liste avec les données
        professeurListView.setAdapter(adapter);


        return rootView;
    }

}
