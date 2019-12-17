package com.tayo.patient.service;


import com.tayo.patient.dao.AdminDao;
import com.tayo.patient.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("adminService")
public class AdminService {


    @Autowired
    AdminDao dao;

    @Autowired
    Environment environment;



    public Response signUp(User user) {

        return dao.signUp(user);
    }

    public Response addPatient(Patient patient) {

        return dao.addPatient(patient);
    }

    public List<Hospital> getHospital(Hospital hospital)
    {
        return dao.getHospital(hospital);
    }

    public List<Patient> getPatient(Patient patient) {

        return dao.getPatient(patient);
    }

    public Response removePatient(Patient patient) {
        return dao.removePatient(patient);
    }

    public Response editPatient(Patient patient) {

        return dao.editPatient(patient);
    }

    public Response addHospital(Hospital hospital) {
        return dao.addHospital(hospital);
    }

    public Response createDiagnosis(Diagnosis diagnosis) {

        return dao.createDiagnosis(diagnosis);
    }

    public Response removeDiagnosis(Diagnosis diagnosis) {
        return dao.removeDiagnosis(diagnosis);
    }

    public Response updateDiagnosis(Diagnosis diagnosis) {
        return dao.updateDiagnosis(diagnosis);
    }

    public List<Diagnosis> getDiagnosis(Diagnosis diagnosis) {
        return dao.getDiagnosis(diagnosis);
    }

    public Response createInvestigation(Investigation investigation) {
        return dao.createInvestigation(investigation);
    }

    public Response removeInvestigation(Investigation investigation) {
        return dao.removeInvestigation(investigation);
    }

    public List<Investigation> getInvestigation(Investigation investigation) {
        return dao.getInvestigation(investigation);
    }
}
