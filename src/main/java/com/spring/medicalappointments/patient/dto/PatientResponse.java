package com.spring.medicalappointments.patient.dto;

import java.time.LocalDate;

public record PatientResponse (String name, String email, LocalDate dob){}
