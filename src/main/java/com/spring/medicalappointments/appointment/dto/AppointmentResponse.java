package com.spring.medicalappointments.appointment.dto;

import com.spring.medicalappointments.appointment.TStatus;
import lombok.NonNull;

import java.time.LocalDateTime;

public record AppointmentResponse(
        @NonNull
        Long id,
        @NonNull
        LocalDateTime date,
        String detail,
        @NonNull
        TStatus status,
        @NonNull
        String doctorName,
        @NonNull
        String patientName
)
{}
