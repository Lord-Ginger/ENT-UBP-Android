package com.ent_ubp_android.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import android.widget.LinearLayout.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class PopupFormationActivity extends Activity implements OnItemClickListener {
    LayoutInflater li;
    LinearLayout main_layout;
    ListView list_view_formation;

    List<String> formation;
    List<String> formation2;

    private void populateFormation(){
        formation = new ArrayList<String>();
        formation.add("Informatique");
        formation.add("Mathématique");
        formation.add("Biologie");
        formation.add("Physique");
        formation.add("Informatique");
        formation.add("Mathématique");
        formation.add("Biologie");
        formation.add("Physique");
        formation.add("Informatique");
        formation.add("Mathématique");
        formation.add("Biologie");
        formation.add("Physique");
        formation.add("Physique");
        formation.add("Informatique");
        formation.add("Mathématique");
        formation.add("Biologie");
        formation.add("Physique");
    }


    private void populateFormation2(){
        formation2 = new ArrayList<String>();
        formation2.add("Info");
        formation2.add("Math");
        formation2.add("Bio");
        formation2.add("Phy");

    }

    private void addNewLevel(LayoutInflater inflater, List<String> items){
        ListView formationListView = (ListView) inflater.inflate(R.layout.popup_list_view, main_layout, false);
        ArrayAdapter<String> levelAdapter = new ArrayAdapter<String>(PopupFormationActivity.this,
                                                                R.layout.popup_text_view,
                                                                items);

        formationListView.setAdapter(levelAdapter);
        formationListView.setOnItemClickListener(this);

        main_layout.addView(formationListView, main_layout.getChildCount()-1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_formation);
        getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        populateFormation2();
        populateFormation();

        li = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        main_layout = (LinearLayout) findViewById(R.id.popup_main);

        list_view_formation = (ListView) findViewById(R.id.list_formation);
        addNewLevel(li, formation);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        parent.setVisibility(View.GONE);
        addNewLevel(li, formation2);

        TextView path = (TextView) main_layout.findViewById(R.id.popup_path);
        String element = ((TextView) view.findViewById(R.id.popup_text_view)).getText().toString();
        String text = (path.getText().toString().equals("")) ? element : path.getText() + " > " + element;
        path.setText(text);
    }
    @Override
    public void onBackPressed() {
        if (main_layout.getChildCount() > 4) {
            main_layout.removeViewAt(main_layout.getChildCount() - 2);
            main_layout.getChildAt(main_layout.getChildCount() - 2).setVisibility(View.VISIBLE);

            TextView path = (TextView) main_layout.findViewById(R.id.popup_path);
            String text = path.getText().toString();
            text = (text.lastIndexOf(" > ") != -1) ? (String) text.subSequence(0, text.lastIndexOf(" > ")) : "";
            path.setText(text);

        }else{
            super.onBackPressed();
        }
    }
}
