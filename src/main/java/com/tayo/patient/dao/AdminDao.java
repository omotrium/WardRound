package com.tayo.patient.dao;


import com.tayo.patient.model.*;

import java.util.List;

public interface AdminDao {

    Response signUp(User request);

    Response addPatient(Patient request);

    List<Hospital> getHospital(Hospital request);

    List<Patient> getPatient(Patient patient);

    Response removePatient(Patient patient);

    Response editPatient(Patient patient);

    Response addHospital(Hospital hospital);

    Response createDiagnosis(Diagnosis diagnosis);

    Response removeDiagnosis(Diagnosis diagnosis);

    Response updateDiagnosis(Diagnosis diagnosis);

    List<Diagnosis> getDiagnosis(Diagnosis diagnosis);

    Response createInvestigation(Investigation investigation);

    Response removeInvestigation(Investigation investigation);

    List<Investigation> getInvestigation(Investigation investigation);
}
