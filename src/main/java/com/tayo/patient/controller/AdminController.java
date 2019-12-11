package com.tayo.patient.controller;


import com.tayo.patient.exception.GlobalRestException;
import com.tayo.patient.model.Hospital;
import com.tayo.patient.model.Patient;
import com.tayo.patient.model.Response;
import com.tayo.patient.model.User;
import com.tayo.patient.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping(value = "/api")
public class AdminController {


    @Autowired
    Environment environment;

    @Autowired
    AdminService adminService;



    final static Logger LOGGER = LoggerFactory.getLogger(AdminController.class);


    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }



        @SuppressWarnings("all")
    @RequestMapping(value = "/signUp", method = RequestMethod.POST, consumes = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<?> signUp(@RequestBody User user) {



            Response response = adminService.signUp(user);


        LOGGER.info("info " + response);


        if (response != null)
            return new ResponseEntity<>(response, HttpStatus.OK);
        else
            throw new GlobalRestException("99", "Oops Something went wrong");





    }


    @SuppressWarnings("all")
    @RequestMapping(value = "/addPatient", method = RequestMethod.POST, consumes = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<?> addPatient(@RequestBody Patient patient) {

        Response response = adminService.addPatient(patient);

        LOGGER.info("patient " + response);

        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            throw new GlobalRestException("99", "Oops Something went wrong");


        }

    }

    @SuppressWarnings("all")
    @RequestMapping(value = "/getHospital", method = RequestMethod.POST, consumes = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<?> getHospital(@RequestBody Hospital hospital) {

        List<Hospital> response = adminService.getHospital(hospital);

        LOGGER.info("patient " + response);

        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            throw new GlobalRestException("99", "Oops Something went wrong");


        }

    }

    @SuppressWarnings("all")
    @RequestMapping(value = "/getPatient", method = RequestMethod.POST, consumes = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<?> getPatient(@RequestBody Patient patient) {

        List<Patient> response = adminService.getPatient(patient);

        LOGGER.info("patient " + response);

        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            throw new GlobalRestException("99", "Oops Something went wrong");


        }

    }

    @SuppressWarnings("all")
    @RequestMapping(value = "/removePatient", method = RequestMethod.POST, consumes = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<?> removePatient(@RequestBody Patient patient) {
        try {

            Response response = adminService.removePatient(patient);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("********Oops Something went wrong **********" + e);
            throw new GlobalRestException("99", e.toString());
        }
    }


    @SuppressWarnings("all")
    @RequestMapping(value = "/editPatient", method = RequestMethod.POST, consumes = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<?> editPatient(@RequestBody Patient patient) {

        Response response = adminService.editPatient(patient);

        LOGGER.info("patient " + response);

        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            throw new GlobalRestException("99", "Oops Something went wrong");


        }

    }

}
