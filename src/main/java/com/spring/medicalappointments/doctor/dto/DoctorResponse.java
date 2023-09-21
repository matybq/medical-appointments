package com.spring.medicalappointments.doctor.dto;

import com.spring.medicalappointments.doctor.TSpecialty;

public record DoctorResponse (
        Long id,
        String name,
        TSpecialty specialty
)
{}
