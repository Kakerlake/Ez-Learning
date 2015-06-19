package com.example.daniel.myapplication;


public class KarteiKarte{

    private int id;
       private String deutsch;
       private String englisch;
        private int wert;
    public KarteiKarte(String deutsch, String englisch) {
        this.deutsch = deutsch;
        this.englisch = englisch;
        this.wert = 0;

    }
    public void setId(int id) {
        this.id=id;
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
    public int getWert() {
        return wert;
    }
    public String getDeutsch(){
        return deutsch;
    }
    public String getEnglisch(){
        return englisch;
    }
    public int getId() {
        return this.id;
    }
}


