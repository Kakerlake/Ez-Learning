package com.example.daniel.myapplication;

import android.widget.Toast;

public class KarteiKarte{
        String deutsch;
        String englisch;
        int wert;
    public KarteiKarte(String deutsch, String englisch) {
        this.deutsch = deutsch;
        this.englisch = englisch;
        wert = 0;

    }

    public void setDeutsch(String deutsch){
        this.deutsch = deutsch;
    }

    public void setEnglisch(String englisch){
        this.englisch = englisch;
    }

    public void setWert(int wert) {
        this.wert = wert;
    }
    public int wert() {
        return wert;
    }

    public String getDeutsch(){
        return deutsch;
    }
//adsdasdasddthdghfghgfhgfhfhfhfghfgh
    public String getEnglisch(){
        return englisch;
    }
}


