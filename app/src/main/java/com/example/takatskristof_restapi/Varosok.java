package com.example.takatskristof_restapi;

public class Varosok {

    String varos,orszag;
    int lakossag;

    public Varosok(String varos, String orszag, int lakossag) {
        this.varos = varos;
        this.orszag = orszag;
        this.lakossag = lakossag;
    }

    public Varosok(){}

    public String getVaros() {
        return varos;
    }

    public String getOrszag() {
        return orszag;
    }

    public int getLakossag() {
        return lakossag;
    }
}
