package com.tayo.patient.dao;



import com.tayo.patient.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository("adminDao")
public class AdminDaoImpl implements AdminDao {

    final static Logger LOGGER = LoggerFactory.getLogger(AdminDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Value("${baseUtilPackage}")
    private String baseUtilityPackage;

    @Autowired
    private Environment environment;



    public Response signUp(User user) {
        SimpleJdbcCall create_affiliateSimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
        create_affiliateSimpleJdbcCall.withProcedureName(baseUtilityPackage + ".sign_up")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(new SqlParameter("P_USERNAME", Types.VARCHAR),
                        new SqlParameter("P_TITLE", Types.VARCHAR),
                        new SqlParameter("P_PHONENUMBER", Types.VARCHAR),
                        new SqlParameter("P_PASSWORD", Types.VARCHAR),

                        new SqlParameter("P_HOSPITAL", Types.VARCHAR),
                        new SqlParameter("P_FULLNAME", Types.VARCHAR),
                        new SqlParameter("P_EMAIL", Types.VARCHAR),
                        new SqlOutParameter("p_code", Types.VARCHAR),
                        new SqlOutParameter("p_message", Types.VARCHAR))
                .compile();

        SqlParameterSource inParams = new MapSqlParameterSource()
                .addValue("P_USERNAME", user.getUserName())
                .addValue("P_TITLE", user.getTitle())
                .addValue("P_PHONENUMBER", user.getPhoneNumber())
                .addValue("P_PASSWORD", user.getPassword())
                .addValue("P_HOSPITAL", user.getHospital())
                .addValue("P_FULLNAME", user.getFullName())
                .addValue("P_EMAIL", user.getEmail());

        Map<String, Object> returningResult = create_affiliateSimpleJdbcCall.execute(inParams);
        String responseCode = (String) returningResult.get("p_code");
        String validResponseCode = responseCode != null ? responseCode : "99";
        String responseMsg = (String) returningResult.get("p_message");
        String validResponseMsg = responseMsg != null ? responseMsg : "";

        Response response = new Response();
        response.setResponseCode(validResponseCode);
        response.setResponseMessage(validResponseMsg);

        return response;
    }

    @Override
    public Response addPatient(Patient request) {

            SimpleJdbcCall create_affiliateSimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            create_affiliateSimpleJdbcCall.withProcedureName(baseUtilityPackage + ".add_patient")
                    .withoutProcedureColumnMetaDataAccess()
                    .declareParameters(new SqlParameter("P_HOSPITAL", Types.VARCHAR),
                            new SqlParameter("P_FIRST_NAME", Types.VARCHAR),
                            new SqlParameter("P_LAST_NAME", Types.VARCHAR),
                            new SqlParameter("P_WARD", Types.VARCHAR),
                            new SqlOutParameter("p_code", Types.VARCHAR),
                            new SqlOutParameter("p_message", Types.VARCHAR))
                    .compile();

            SqlParameterSource inParams = new MapSqlParameterSource()
                    .addValue("P_HOSPITAL", request.getHospital())
                    .addValue("P_FIRST_NAME", request.getFirstName())
                    .addValue("P_LAST_NAME", request.getLastName())
                    .addValue("P_WARD", request.getWard());


            Map<String, Object> returningResult = create_affiliateSimpleJdbcCall.execute(inParams);
            String responseCode = (String) returningResult.get("p_code");
            String validResponseCode = responseCode != null ? responseCode : "99";
            String responseMsg = (String) returningResult.get("p_message");
            String validResponseMsg = responseMsg != null ? responseMsg : "";

            Response response = new Response();
            response.setResponseCode(validResponseCode);
            response.setResponseMessage(validResponseMsg);

            return response;
        }

    @Override
    public List<Hospital> getHospital(Hospital request) {


        SimpleJdbcCall get_returns_on_cbn_salesSimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);


        get_returns_on_cbn_salesSimpleJdbcCall.withProcedureName(baseUtilityPackage + ".get_hospital")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(new SqlParameter("p_email", Types.VARCHAR),
                        new SqlOutParameter("r_details", Types.REF_CURSOR))
                .returningResultSet("r_details", new RowMapper<Hospital>() {
                    @Override
                    public Hospital mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Hospital request = new Hospital();

                        request.setName(rs.getString(1));
                        request.setLocation(rs.getString(2));
                        request.setId(rs.getString(3));


                        return request;
                    }
                });
        get_returns_on_cbn_salesSimpleJdbcCall.compile();
        SqlParameterSource inparams = new MapSqlParameterSource().addValue("p_email", request);
        Map<String, Object> returningResultSet = get_returns_on_cbn_salesSimpleJdbcCall.execute(inparams);
        List<Hospital> response = (List<Hospital>) returningResultSet.get("r_details");
        return response == null || response.isEmpty() ? new ArrayList<>() : response;
    }

    @Override
    public List<Patient> getPatient(Patient patient) {
        SimpleJdbcCall get_returns_on_cbn_salesSimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);


        get_returns_on_cbn_salesSimpleJdbcCall.withProcedureName(baseUtilityPackage + ".get_patient")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(new SqlParameter("p_email", Types.VARCHAR),
                        new SqlOutParameter("r_details", Types.REF_CURSOR))
                .returningResultSet("r_details", new RowMapper<Patient>() {
                    @Override
                    public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Patient request = new Patient();

                        request.setFirstName(rs.getString(1));
                        request.setLastName(rs.getString(2));
                        request.setHospital(rs.getString(3));
                        request.setWard(rs.getString(4));

                        return request;
                    }
                });
        get_returns_on_cbn_salesSimpleJdbcCall.compile();
        SqlParameterSource inparams = new MapSqlParameterSource().addValue("p_email", patient);
        Map<String, Object> returningResultSet = get_returns_on_cbn_salesSimpleJdbcCall.execute(inparams);
        List<Patient> response = (List<Patient>) returningResultSet.get("r_details");
        return response == null || response.isEmpty() ? new ArrayList<>() : response;
    }

    @Override
    public Response removePatient(Patient patient) {

        System.out.println("am here in backend"+patient.getId());

        Response response = new Response();
        SimpleJdbcCall update_daily_volumeSimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
        update_daily_volumeSimpleJdbcCall.withProcedureName(baseUtilityPackage + ".remove_patient")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("p_id", Types.VARCHAR),
                        new SqlOutParameter("p_code", Types.VARCHAR),
                        new SqlOutParameter("p_status", Types.VARCHAR))
                .compile();
        SqlParameterSource inParams = new MapSqlParameterSource()
                .addValue("p_id", patient.getId());
        Map<String, Object> returningResult = update_daily_volumeSimpleJdbcCall.execute(inParams);
        String responseCode = (String) returningResult.get("p_code");
        String validResponseCode = responseCode != null ? responseCode : "99";
        String responseMsg = (String) returningResult.get("p_status");
        String validResponseMsg = responseMsg != null ? responseMsg : "";
        response.setResponseCode(validResponseCode);
        response.setResponseMessage(validResponseMsg);
        return response;
    }

    @Override
    public Response editPatient(Patient patient) {
        SimpleJdbcCall create_affiliateSimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
        create_affiliateSimpleJdbcCall.withProcedureName(baseUtilityPackage + ".edit_patient")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(new SqlParameter("P_HOSPITAL", Types.VARCHAR),
                        new SqlParameter("P_FIRST_NAME", Types.VARCHAR),
                        new SqlParameter("P_LAST_NAME", Types.VARCHAR),
                        new SqlParameter("P_WARD", Types.VARCHAR),
                        new SqlParameter("P_ID", Types.VARCHAR),
                        new SqlOutParameter("p_code", Types.VARCHAR),
                        new SqlOutParameter("p_message", Types.VARCHAR))
                .compile();

        SqlParameterSource inParams = new MapSqlParameterSource()
                .addValue("P_HOSPITAL", patient.getHospital())
                .addValue("P_FIRST_NAME", patient.getFirstName())
                .addValue("P_LAST_NAME", patient.getLastName())
                .addValue("P_WARD", patient.getWard())
                .addValue("P_ID", patient.getId());


        Map<String, Object> returningResult = create_affiliateSimpleJdbcCall.execute(inParams);
        String responseCode = (String) returningResult.get("p_code");
        String validResponseCode = responseCode != null ? responseCode : "99";
        String responseMsg = (String) returningResult.get("p_message");
        String validResponseMsg = responseMsg != null ? responseMsg : "";

        Response response = new Response();
        response.setResponseCode(validResponseCode);
        response.setResponseMessage(validResponseMsg);

        return response;
    }

    @Override
    public Response addHospital(Hospital hospital) {

        SimpleJdbcCall create_affiliateSimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
        create_affiliateSimpleJdbcCall.withProcedureName(baseUtilityPackage + ".add_hospital")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(new SqlParameter("P_NAME", Types.VARCHAR),
                        new SqlParameter("P_LOCATION", Types.VARCHAR),
                        new SqlOutParameter("p_code", Types.VARCHAR),
                        new SqlOutParameter("p_message", Types.VARCHAR))
                .compile();

        SqlParameterSource inParams = new MapSqlParameterSource()
                .addValue("P_NAME", hospital.getName())
                .addValue("P_LOCATION", hospital.getLocation());


        Map<String, Object> returningResult = create_affiliateSimpleJdbcCall.execute(inParams);
        String responseCode = (String) returningResult.get("p_code");
        String validResponseCode = responseCode != null ? responseCode : "99";
        String responseMsg = (String) returningResult.get("p_message");
        String validResponseMsg = responseMsg != null ? responseMsg : "";

        Response response = new Response();
        response.setResponseCode(validResponseCode);
        response.setResponseMessage(validResponseMsg);

        return response;
    }

    @Override
    public Response createDiagnosis(Diagnosis diagnosis) {
        SimpleJdbcCall create_affiliateSimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
        create_affiliateSimpleJdbcCall.withProcedureName(baseUtilityPackage + ".create_diagnosis")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(new SqlParameter("P_PRESCRIPTION", Types.VARCHAR),
                        new SqlOutParameter("p_code", Types.VARCHAR),
                        new SqlOutParameter("p_message", Types.VARCHAR))
                .compile();

        SqlParameterSource inParams = new MapSqlParameterSource()
                .addValue("P_PRESCRIPTION", diagnosis.getPrescription());


        Map<String, Object> returningResult = create_affiliateSimpleJdbcCall.execute(inParams);
        String responseCode = (String) returningResult.get("p_code");
        String validResponseCode = responseCode != null ? responseCode : "99";
        String responseMsg = (String) returningResult.get("p_message");
        String validResponseMsg = responseMsg != null ? responseMsg : "";

        Response response = new Response();
        response.setResponseCode(validResponseCode);
        response.setResponseMessage(validResponseMsg);

        return response;
    }

    @Override
    public Response removeDiagnosis(Diagnosis diagnosis) {


        Response response = new Response();
        SimpleJdbcCall update_daily_volumeSimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
        update_daily_volumeSimpleJdbcCall.withProcedureName(baseUtilityPackage + ".remove_diagnosis")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("p_id", Types.VARCHAR),
                        new SqlOutParameter("p_code", Types.VARCHAR),
                        new SqlOutParameter("p_status", Types.VARCHAR))
                .compile();
        SqlParameterSource inParams = new MapSqlParameterSource()
                .addValue("p_id", diagnosis.getId());
        Map<String, Object> returningResult = update_daily_volumeSimpleJdbcCall.execute(inParams);
        String responseCode = (String) returningResult.get("p_code");
        String validResponseCode = responseCode != null ? responseCode : "99";
        String responseMsg = (String) returningResult.get("p_status");
        String validResponseMsg = responseMsg != null ? responseMsg : "";
        response.setResponseCode(validResponseCode);
        response.setResponseMessage(validResponseMsg);
        return response;
    }

    @Override
    public Response updateDiagnosis(Diagnosis diagnosis) {

            SimpleJdbcCall create_affiliateSimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            create_affiliateSimpleJdbcCall.withProcedureName(baseUtilityPackage + ".update_diagnosis")
                    .withoutProcedureColumnMetaDataAccess()
                    .declareParameters(new SqlParameter("P_ID", Types.VARCHAR),
                            new SqlParameter("P_PRESCRIPTION", Types.VARCHAR),
                            new SqlOutParameter("p_code", Types.VARCHAR),
                            new SqlOutParameter("p_message", Types.VARCHAR))
                    .compile();

            SqlParameterSource inParams = new MapSqlParameterSource()
                    .addValue("P_ID", diagnosis.getId())
                    .addValue("P_PRESCRIPTION", diagnosis.getPrescription());


            Map<String, Object> returningResult = create_affiliateSimpleJdbcCall.execute(inParams);
            String responseCode = (String) returningResult.get("p_code");
            String validResponseCode = responseCode != null ? responseCode : "99";
            String responseMsg = (String) returningResult.get("p_message");
            String validResponseMsg = responseMsg != null ? responseMsg : "";

            Response response = new Response();
            response.setResponseCode(validResponseCode);
            response.setResponseMessage(validResponseMsg);

            return response;
        }

    @Override
    public List<Diagnosis> getDiagnosis(Diagnosis diagnosis) {

        SimpleJdbcCall get_returns_on_cbn_salesSimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);


        get_returns_on_cbn_salesSimpleJdbcCall.withProcedureName(baseUtilityPackage + ".get_diagnosis")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(new SqlParameter("p_email", Types.VARCHAR),
                        new SqlOutParameter("r_details", Types.REF_CURSOR))
                .returningResultSet("r_details", new RowMapper<Diagnosis>() {
                    @Override
                    public Diagnosis mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Diagnosis request = new Diagnosis();

                        request.setPrescription(rs.getString(1));

                        return request;
                    }
                });
        get_returns_on_cbn_salesSimpleJdbcCall.compile();
        SqlParameterSource inparams = new MapSqlParameterSource().addValue("p_email", diagnosis);
        Map<String, Object> returningResultSet = get_returns_on_cbn_salesSimpleJdbcCall.execute(inparams);
        List<Diagnosis> response = (List<Diagnosis>) returningResultSet.get("r_details");
        return response == null || response.isEmpty() ? new ArrayList<>() : response;
    }

    @Override
    public Response createInvestigation(Investigation investigation) {
        SimpleJdbcCall create_affiliateSimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
        create_affiliateSimpleJdbcCall.withProcedureName(baseUtilityPackage + ".create_investigation")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(new SqlParameter("P_NOTE", Types.VARCHAR),
                        new SqlParameter("P_PATIENT_NAME", Types.VARCHAR),
                        new SqlOutParameter("p_code", Types.VARCHAR),
                        new SqlOutParameter("p_message", Types.VARCHAR))
                .compile();

        SqlParameterSource inParams = new MapSqlParameterSource()
                .addValue("P_NOTE", investigation.getNote())
                .addValue("P_PATIENT_NAME", investigation.getPatient().getFirstName());


        Map<String, Object> returningResult = create_affiliateSimpleJdbcCall.execute(inParams);
        String responseCode = (String) returningResult.get("p_code");
        String validResponseCode = responseCode != null ? responseCode : "99";
        String responseMsg = (String) returningResult.get("p_message");
        String validResponseMsg = responseMsg != null ? responseMsg : "";

        Response response = new Response();
        response.setResponseCode(validResponseCode);
        response.setResponseMessage(validResponseMsg);

        return response;
    }

    @Override
    public Response removeInvestigation(Investigation investigation) {
         Response response = new Response();
        SimpleJdbcCall update_daily_volumeSimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
        update_daily_volumeSimpleJdbcCall.withProcedureName(baseUtilityPackage + ".remove_investigation")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("p_id", Types.VARCHAR),
                        new SqlOutParameter("p_code", Types.VARCHAR),
                        new SqlOutParameter("p_status", Types.VARCHAR))
                .compile();
        SqlParameterSource inParams = new MapSqlParameterSource()
                .addValue("p_id", investigation.getId());
        Map<String, Object> returningResult = update_daily_volumeSimpleJdbcCall.execute(inParams);
        String responseCode = (String) returningResult.get("p_code");
        String validResponseCode = responseCode != null ? responseCode : "99";
        String responseMsg = (String) returningResult.get("p_status");
        String validResponseMsg = responseMsg != null ? responseMsg : "";
        response.setResponseCode(validResponseCode);
        response.setResponseMessage(validResponseMsg);
        return response;
    }

    @Override
    public List<Investigation> getInvestigation(Investigation investigation) {
        SimpleJdbcCall get_returns_on_cbn_salesSimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
         get_returns_on_cbn_salesSimpleJdbcCall.withProcedureName(baseUtilityPackage + ".get_investigation")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(new SqlParameter("p_email", Types.VARCHAR),
                        new SqlOutParameter("r_details", Types.REF_CURSOR))
                .returningResultSet("r_details", new RowMapper<Diagnosis>() {
                    @Override
                    public Diagnosis mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Diagnosis request = new Diagnosis();

                        request.setPrescription(rs.getString(1));

                        return request;
                    }
                });
        get_returns_on_cbn_salesSimpleJdbcCall.compile();
        SqlParameterSource inparams = new MapSqlParameterSource().addValue("p_email", investigation);
        Map<String, Object> returningResultSet = get_returns_on_cbn_salesSimpleJdbcCall.execute(inparams);
        List<Investigation> response = (List<Investigation>) returningResultSet.get("r_details");
        return response == null || response.isEmpty() ? new ArrayList<>() : response;
    }
}



