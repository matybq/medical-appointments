package com.spring.medicalappointments.doctor.dto;

import com.spring.medicalappointments.doctor.TSpecialty;
import jakarta.persistence.Column;
import lombok.NonNull;

public record DoctorRequest (
         String name,
         @NonNull
         String username,
         @NonNull
         String password,
         TSpecialty specialty
)
{}
