package com.ent_ubp_android.app.fragment.classroom;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.ent_ubp_android.app.R;
import com.ent_ubp_android.app.adapter.recyclerview.RecyclerViewClassroomAdapter;
import com.ent_ubp_android.app.exchange.serveur.SingletonUbpRestTemplate;
import com.ent_ubp_android.app.fragment.FragmentSwitcher;
import com.ent_ubp_android.app.model.classroom.Classroom;
import com.ent_ubp_android.app.model.classroom.ClassroomType;
import com.ent_ubp_android.app.model.classroom.RoomCapacity;
import com.ent_ubp_android.app.model.classroom.equipement.EquipmentType;
import com.ent_ubp_android.app.model.classroom.equipement.Quantity;
import com.ent_ubp_android.app.model.classroom.equipement.RoomEquipment;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.HttpClientErrorException;

import java.lang.ref.WeakReference;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassroomAddFragment extends Fragment implements CompoundButton.OnCheckedChangeListener, Button.OnClickListener {

    private WeakReference<HttpRequestTask> asyncTaskWeakRef;

    List<EquipmentType> listEquipmentType;

    EditText nomSalle;
    EditText capacity;

    AppCompatCheckBox checkBoxTypeCM;
    AppCompatCheckBox checkBoxTypeTD;
    AppCompatCheckBox checkBoxTypeTP;
    AppCompatCheckBox checkBoxEquipment1;
    AppCompatCheckBox checkBoxEquipment2;

    public ClassroomAddFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listEquipmentType = new ArrayList<>();

        listEquipmentType.add(new EquipmentType("Ordinateur"));
        listEquipmentType.add(new EquipmentType("Rétroprojecteur"));

        listEquipmentType.get(0).setId(Long.parseLong("1"));
        listEquipmentType.get(1).setId(Long.parseLong("2"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_classroom_add, container, false);

        nomSalle = (EditText) view.findViewById(R.id.classroom_add_edit_salle);
        capacity = (EditText) view.findViewById(R.id.classroom_add_edit_capa);

        checkBoxTypeCM = (AppCompatCheckBox) view.findViewById(R.id.checkbox_cm);
        checkBoxTypeTD = (AppCompatCheckBox) view.findViewById(R.id.checkbox_td);
        checkBoxTypeTP = (AppCompatCheckBox) view.findViewById(R.id.checkbox_tp);

        checkBoxEquipment1 = (AppCompatCheckBox) view.findViewById(R.id.checkbox_equipment);
        checkBoxEquipment2 = (AppCompatCheckBox) view.findViewById(R.id.checkbox_equipment2);

        checkBoxTypeCM.setOnCheckedChangeListener(this);
        checkBoxTypeTD.setOnCheckedChangeListener(this);
        checkBoxTypeTP.setOnCheckedChangeListener(this);

        checkBoxEquipment1.setOnCheckedChangeListener(this);
        checkBoxEquipment2.setOnCheckedChangeListener(this);

        AppCompatButton submitButton = new AppCompatButton(getActivity());
        submitButton.setText("Valider");
        submitButton.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.button_validation));
        submitButton.setTextColor(ContextCompat.getColor(getActivity(), android.R.color.white));
        submitButton.setOnClickListener(this);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(10, 10, 10, 10);

        LinearLayout ll = (LinearLayout) view.findViewById(R.id.classroom_add_linearLayout);
        ll.addView(submitButton, lp);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked) {
            buttonView.setChecked(true);
        }else
            buttonView.setChecked(false);
    }

    private boolean isCheckType(){
        if(checkBoxTypeCM.isChecked())
            return true;
        if(checkBoxTypeTD.isChecked())
            return true;
        if(checkBoxTypeTP.isChecked())
            return true;
        return false;
    }

    private boolean isNumber(String number){
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher match = pattern.matcher(number);

        return match.find();
    }

    @Override
    public void onClick(View v) {
        if(StringUtils.isBlank(nomSalle.getText()) || StringUtils.isBlank(capacity.getText())){
            Toast.makeText(getActivity(), "Veuillez entrer un nom et un capacité.", Toast.LENGTH_LONG).show();
        } else if(!isNumber(capacity.getText().toString())){
            Toast.makeText(getActivity(), "Veuillez entrer un nombre pour la capacité", Toast.LENGTH_LONG).show();
        } else if (!isCheckType()){
            Toast.makeText(getActivity(), "Veuillez entrer au moins un type de salle", Toast.LENGTH_LONG).show();
        } else {
            Set<ClassroomType> types = new HashSet<>();
            if(checkBoxTypeCM.isChecked())
                types.add(ClassroomType.CM);
            if(checkBoxTypeTD.isChecked())
                types.add(ClassroomType.TD);
            if(checkBoxTypeTP.isChecked())
                types.add(ClassroomType.TP);

            RoomCapacity roomCapacity = new RoomCapacity(Integer.parseInt(capacity.getText().toString()));
            Classroom classroom = new Classroom(nomSalle.getText().toString(), roomCapacity, types);

            if(checkBoxEquipment1.isChecked())
                classroom.addEquipment(new RoomEquipment(new EquipmentType(checkBoxEquipment1.getText().toString()), new Quantity(1)));
            if(checkBoxEquipment2.isChecked())
                classroom.addEquipment(new RoomEquipment(new EquipmentType(checkBoxEquipment2.getText().toString()), new Quantity(1)));

            startNewAsyncTask(classroom);
        }
    }

    //Make the AsyncTask
    private void startNewAsyncTask(Classroom classroom){
        HttpRequestTask asyncTask = new HttpRequestTask(this, classroom);
        this.asyncTaskWeakRef = new WeakReference<>(asyncTask);
        asyncTask.execute();
    }

    private boolean isAsyncTaskPending(){
        return this.asyncTaskWeakRef != null &&
                this.asyncTaskWeakRef.get() != null &&
                !this.asyncTaskWeakRef.get().getStatus().equals(AsyncTask.Status.FINISHED);
    }

    /* ************************************************* */
    /*  Inner Class make the transaction with the server */
    /* ************************************************* */
    private class HttpRequestTask extends AsyncTask<Void, Void, Classroom> {

        private WeakReference<ClassroomAddFragment> fragmentWeakRef;
        private Classroom classroom;

        private HttpRequestTask(ClassroomAddFragment fragment, Classroom classroom){
            this.fragmentWeakRef = new WeakReference<>(fragment);
            this.classroom = classroom;
        }

        @Override
        protected Classroom doInBackground(Void... params) {
            try {
                final String url = SingletonUbpRestTemplate.BASE_URL + "classroom";
                SingletonUbpRestTemplate restTemplate = SingletonUbpRestTemplate.getRestTemplate();
                return restTemplate.postForObject(url, this.classroom, Classroom.class);
            }
            catch (HttpClientErrorException e){
                return null;
            }
            catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Classroom classroom) {
            super.onPostExecute(classroom);

            if(classroom != null){
                Toast.makeText(getActivity(), "Salle de cours ajouté avec succés", Toast.LENGTH_SHORT).show();
                FragmentSwitcher.startAnotherFragment(new ClassroomMainFragment());
            } else{
                Toast.makeText(getActivity(), "Erreur dans la saisie de la salle", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
