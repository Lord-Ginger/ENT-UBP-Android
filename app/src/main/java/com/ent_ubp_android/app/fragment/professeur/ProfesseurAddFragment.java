package com.ent_ubp_android.app.fragment.professeur;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import com.ent_ubp_android.app.R;
import com.ent_ubp_android.app.model.teacher.Teacher;
import com.ent_ubp_android.app.model.teacher.TeacherType;
import com.ent_ubp_android.app.model.teacher.contact.Contact;
import com.ent_ubp_android.app.model.teacher.contact.adresse.Address;
import com.ent_ubp_android.app.model.teacher.contact.adresse.AddressDetails;
import com.ent_ubp_android.app.model.teacher.contact.email.Email;
import com.ent_ubp_android.app.model.teacher.contact.email.EmailDetails;
import com.ent_ubp_android.app.model.teacher.contact.phone.Phone;
import com.ent_ubp_android.app.model.teacher.contact.phone.PhoneDetails;
import com.ent_ubp_android.app.model.teacher.name.Name;

//TODO: Link with server
public class ProfesseurAddFragment extends Fragment {

    EditText nom;
    EditText prenom;
    EditText num_rue;
    EditText rue;
    EditText zip;
    EditText ville;
    EditText phone;
    EditText mail;

    Button addButton;
    RadioButton univ_rb;
    RadioButton inter_rb;

    public ProfesseurAddFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_professeur_add, container, false);

        nom = (EditText) v.findViewById(R.id.prof_add_nom);
        prenom = (EditText) v.findViewById(R.id.prof_add_prenom);
        phone = (EditText) v.findViewById(R.id.prof_add_phone);
        mail = (EditText) v.findViewById(R.id.prof_add_mail);
        num_rue = (EditText) v.findViewById(R.id.prof_add_num_rue);
        rue = (EditText) v.findViewById(R.id.prof_add_rue);
        zip = (EditText) v.findViewById(R.id.prof_add_zip);
        ville= (EditText) v.findViewById(R.id.prof_add_ville);

        univ_rb = (RadioButton)v.findViewById(R.id.radio_universitaire);
        inter_rb = (RadioButton) v.findViewById(R.id.radio_intervenant);

        addButton = (Button) v.findViewById(R.id.button_add_prof);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addProfesseur();
            }
        });


        return v;
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

    private void addProfesseur(){
        Name name = new Name(prenom.getText().toString(),nom.getText().toString());
        Contact contact = new Contact();
        TeacherType teacherType;


        AddressDetails adD = new AddressDetails(num_rue.getText().toString(),
                                                rue.getText().toString(),
                                                zip.getText().toString(),
                                                ville.getText().toString());
        Address ad = new Address(adD);

        PhoneDetails phD = new PhoneDetails(phone.getText().toString());
        Phone ph = new Phone(phD);

        EmailDetails emD = new EmailDetails(mail.getText().toString());
        Email em = new Email(emD);

        contact.addAddress(ad);
        contact.addEmail(em);
        contact.addPhone(ph);

        if (univ_rb.isChecked()) {
            teacherType = TeacherType.UNIVERSITY_TEACHER;
        }
        else {
            teacherType = TeacherType.OUTSIDER_TEACHER;
        }

        Teacher t = new Teacher(name,contact,teacherType);

    }



}
