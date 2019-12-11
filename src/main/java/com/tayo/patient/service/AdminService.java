package com.tayo.patient.service;


import com.tayo.patient.dao.AdminDao;
import com.tayo.patient.model.Hospital;
import com.tayo.patient.model.Patient;
import com.tayo.patient.model.Response;
import com.tayo.patient.model.User;
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
}
