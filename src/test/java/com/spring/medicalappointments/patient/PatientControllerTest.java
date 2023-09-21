package com.spring.medicalappointments.patient;

import com.spring.medicalappointments.patient.dto.PatientDTOMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class PatientControllerTest {

    private PatientRepository patientRepositoryUnderTest;
    private PatientService patientServiceUnderTest;
    @Autowired
    private PatientDTOMapper patientDTOMapper;

    @BeforeEach
    void setUp() {
        patientServiceUnderTest = new PatientService(patientRepositoryUnderTest, patientDTOMapper);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void getAllPatients() {
    }

    @Test
    void getPatientById() {
    }

    @Test
    void getPatientByName() {
    }

    @Test
    void addPatient() {
    }

    @Test
    void updatePatient() {
    }

    @Test
    void deletePatient() {
    }
}