package com.tayo.patient.dao;


import com.tayo.patient.model.Hospital;
import com.tayo.patient.model.Patient;
import com.tayo.patient.model.Response;
import com.tayo.patient.model.User;

import java.util.List;

public interface AdminDao {

    Response signUp(User request);

    Response addPatient(Patient request);

    List<Hospital> getHospital(Hospital request);

    List<Patient> getPatient(Patient patient);

    Response removePatient(Patient patient);

    Response editPatient(Patient patient);
}
