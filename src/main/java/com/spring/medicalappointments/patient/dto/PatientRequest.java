package com.spring.medicalappointments.patient.dto;

import lombok.NonNull;

import java.time.LocalDate;

public record PatientRequest(@NonNull String name, @NonNull String email, LocalDate dob){}
