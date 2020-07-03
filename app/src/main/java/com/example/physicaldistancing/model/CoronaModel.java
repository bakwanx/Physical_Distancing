package com.example.physicaldistancing.model;

import android.os.Bundle;

import org.json.JSONObject;

public class CoronaModel {
    String positif, meninggal, sembuh;

    public CoronaModel(JSONObject jsonObject) {
        try{
            String getPositif = jsonObject.getString("positif");
            String getMeninggal = jsonObject.getString("meninggal");
            String getSembuh = jsonObject.getString("sembuh");

            this.positif = getPositif;
            this.meninggal = getMeninggal;
            this.sembuh = getSembuh;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CoronaModel() {

    }

    public void setPositif(String positif) {
        this.positif = positif;
    }

    public void setMeninggal(String meninggal) {
        this.meninggal = meninggal;
    }

    public void setSembuh(String sembuh) {
        this.sembuh = sembuh;
    }


    public String getPositif() {
        return positif;
    }

    public String getMeninggal() {
        return meninggal;
    }

    public String getSembuh() {
        return sembuh;
    }
}
