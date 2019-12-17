package com.tayo.patient.model;

public class Investigation {

    private String id;
    private String note;
    private Patient patient;

    public Investigation() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Investigation{" +
                "id='" + id + '\'' +
                ", note='" + note + '\'' +
                ", patient=" + patient +
                '}';
    }
}
