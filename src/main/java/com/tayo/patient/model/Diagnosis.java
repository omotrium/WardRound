package com.tayo.patient.model;

public class Diagnosis {


    private String id;
    private  String prescription;


    public Diagnosis(){}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }


    @Override
    public String toString() {
        return "Diagnosis{" +
                "id='" + id + '\'' +
                ", prescription='" + prescription + '\'' +
                '}';
    }
}
