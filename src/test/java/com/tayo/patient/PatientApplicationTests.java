package com.tayo.patient;

import com.tayo.patient.model.Patient;
import com.tayo.patient.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PatientApplicationTests {


    @Autowired
    AdminService adminService;



    @Test
    void contextLoads() {
    }


//    @Test
//    public void checkValid13DigitISBN(){
//
//        Patient patient = new Patient();
//        boolean result = adminService.addPatient(patient);
//        //assertTrue("first value", result);
//
//    }

}
