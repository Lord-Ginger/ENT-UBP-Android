package com.ent_ubp_android.app.model;

import java.util.Calendar;

public class Agenda {
    private Calendar jours;
    private String heure;
    private String salle;
    private String cours;

    public Agenda(Calendar jours, String heure, String cours, String salle){
        this.jours = jours;
        this.heure = heure;
        this.cours = cours;
        this.salle = salle;
    }

    public String getHeure() {
        return heure;
    }
    public String getSalle() {
        return salle;
    }
    public String getCours() {
        return cours;
    }
    public Calendar getJours() {
        return jours;
    }
}
