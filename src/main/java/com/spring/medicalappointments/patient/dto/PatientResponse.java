package com.spring.medicalappointments.patient.dto;

import java.time.LocalDate;

public record PatientResponse (Long id, String name, String email, LocalDate dob, Integer age){}
