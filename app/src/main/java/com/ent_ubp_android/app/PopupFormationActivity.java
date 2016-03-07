package com.ent_ubp_android.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import com.ent_ubp_android.app.Interface.IRecyclerViewClickListener;
import com.ent_ubp_android.app.adapter.FormationAdapter;
import com.ent_ubp_android.app.model.Formation;

import java.util.ArrayList;
import java.util.List;

public class PopupFormationActivity extends AppCompatActivity implements IRecyclerViewClickListener{

    private List<Formation> formation;
    private List<Formation> formation2;


    private void populateFormation(){
        formation = new ArrayList<Formation>();
        formation.add(new Formation("Informatique"));
        formation.add(new Formation("Mathématique"));
        formation.add(new Formation("Biologie"));
        formation.add(new Formation("Physique"));
        formation.add(new Formation("Informatique"));
        formation.add(new Formation("Mathématique"));
        formation.add(new Formation("Biologie"));
        formation.add(new Formation("Physique"));
        formation.add(new Formation("Informatique"));
        formation.add(new Formation("Mathématique"));
        formation.add(new Formation("Biologie"));
        formation.add(new Formation("Physique"));
        formation.add(new Formation("Informatique"));
        formation.add(new Formation("Mathématique"));
        formation.add(new Formation("Biologie"));
        formation.add(new Formation("Physique"));
    }


    private void populateFormation2(){
        formation2 = new ArrayList<Formation>();
        formation2.add(new Formation("Info"));
        formation2.add(new Formation("Math"));
        formation2.add(new Formation("Bio"));
        formation2.add(new Formation("Phy"));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_formation);
        getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        populateFormation();
        populateFormation2();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.popup_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new FormationAdapter(this, this, formation));
    }

    @Override
    public void onRecyclerViewItemClicked(View view, int position) {
        FormationAdapter adapter = (FormationAdapter)((RecyclerView)view.getParent()).getAdapter();
        adapter.changeDataSet(formation2);
    }
}
