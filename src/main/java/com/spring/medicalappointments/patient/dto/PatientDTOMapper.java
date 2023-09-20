package com.spring.medicalappointments.patient.dto;

import com.spring.medicalappointments.patient.Patient;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PatientDTOMapper implements Function<Patient, PatientResponse> {

    @Override
    public PatientResponse apply(Patient patient) {
        return new PatientResponse(
                patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getDob(),
                patient.getAge()
        );
    }

}
