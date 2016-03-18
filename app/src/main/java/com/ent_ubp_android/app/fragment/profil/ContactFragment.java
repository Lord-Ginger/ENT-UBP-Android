package com.ent_ubp_android.app.fragment.profil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
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

import java.util.Iterator;
import java.util.Set;


public class ContactFragment extends Fragment {

    Teacher professeur;

    TextView nom_tv;
    TextView prenom_tv;
    TextView tel_tv;
    TextView addresse_tv;
    TextView mail_tv;
    TextView type_tv;

    public ContactFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contact, container, false);

        /*TODO sera remplacé par la récupération du prof*/



        Name name = new Name("Thirry","Benois");
        Contact contact = new Contact();


        AddressDetails adD = new AddressDetails("223","lot la Pouponne","63750","St Denis du peloton");
        Address ad = new Address(adD);

        PhoneDetails phD = new PhoneDetails("0477859463");
        Phone ph = new Phone(phD);

        EmailDetails emD = new EmailDetails("poponon@gmail.com");
        Email em = new Email(emD);

        contact.addAddress(ad);
        contact.addEmail(em);
        contact.addPhone(ph);

        TeacherType teacherType = TeacherType.UNIVERSITY_TEACHER;


        /*------------------------------------------------------*/




        professeur = new Teacher(name,contact,teacherType);

        Set<Phone> telephones;
        Set<Address> adresses;
        Set<Email> courriels;


        addresse_tv = (TextView) v.findViewById(R.id.contact_adresse);
        mail_tv = (TextView) v.findViewById(R.id.contact_mail);
        tel_tv = (TextView) v.findViewById(R.id.contact_phone);
        nom_tv = (TextView) v.findViewById(R.id.contact_nom);
        prenom_tv = (TextView) v.findViewById(R.id.contact_prenom);
        type_tv = (TextView) v.findViewById(R.id.contact_type);

        prenom_tv.setText(professeur.getName().getFirstName());
        nom_tv.setText(professeur.getName().getLastName());


        if(professeur.getType().toString().compareTo(TeacherType.UNIVERSITY_TEACHER.toString())==0){
            type_tv.setText(R.string.professeur_type_university_string);
        }else{
            type_tv.setText(R.string.professeur_type_outsider_string);
        }

        courriels = professeur.getContact().getEmails();
        telephones = professeur.getContact().getPhones();
        adresses = professeur.getContact().getAddresses();

        /*récupération du premier SET*/
        Iterator itC = courriels.iterator();
        Iterator itT = telephones.iterator();
        Iterator itA = adresses.iterator();

        Address tempad = (Address) itA.next();
        Email tempmail = (Email) itC.next();
        Phone tempphone = (Phone) itT.next();
        /*---------------------------*/


        mail_tv.setText(tempmail.getDetails().getAddress());
        tel_tv.setText(tempphone.getDetails().getNumber());
        addresse_tv.setText(tempad.getDetails().getAllAddress());


        return v;
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
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

}
