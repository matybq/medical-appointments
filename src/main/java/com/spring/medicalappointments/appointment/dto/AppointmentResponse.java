package com.spring.medicalappointments.appointment.dto;

import com.spring.medicalappointments.appointment.TStatus;
import lombok.NonNull;

import java.util.Date;

public record AppointmentResponse(
        @NonNull
        Long id,
        @NonNull
        Date date,
        String detail,
        @NonNull
        TStatus status,
        @NonNull
        String doctorName,
        @NonNull
        String patientName
)
{}
