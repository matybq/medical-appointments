package com.spring.medicalappointments.doctor.dto;

import com.spring.medicalappointments.doctor.Doctor;
import com.spring.medicalappointments.patient.dto.PatientResponse;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DoctorDTOMapper implements Function<Doctor, DoctorResponse> {
    @Override
    public DoctorResponse apply(Doctor doctor) {
        return new DoctorResponse(
                doctor.getId(),
                doctor.getName(),
                doctor.getSpecialty()
        );
    }
}
