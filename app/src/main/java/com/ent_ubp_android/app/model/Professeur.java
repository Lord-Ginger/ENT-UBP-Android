package com.ent_ubp_android.app.model;

import java.util.ArrayList;

/**
 * Created by SIN on 01/03/2016.
 */
public class Professeur {
    private String nom;
    private String prenom;
    private int hourPrev;
    private int hourDone;

    public Professeur(String n,String p,int hPrev){
        nom = n;
        prenom = p;
        hourPrev = hPrev;
        hourDone = 0;
    }

    public int getHourDone() {
        return hourDone;
    }

    public int getHourPrev() {
        return hourPrev;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public boolean allHourDone(){
        boolean result;
        if(this.hourDone==this.hourPrev){
            result=true;
        }else{
            result=false;
        }
        return result;
    }

    public void incrementHourDone(int hourDone) {
        if(!this.allHourDone()) {
            this.hourDone++;
        }else {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
