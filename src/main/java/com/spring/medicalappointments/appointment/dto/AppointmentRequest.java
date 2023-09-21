package com.spring.medicalappointments.appointment.dto;

import com.spring.medicalappointments.appointment.TStatus;
import lombok.NonNull;

import java.time.LocalDateTime;

public record AppointmentRequest(
        @NonNull
        LocalDateTime date,
        String detail,
        @NonNull
        TStatus status,
        @NonNull
        Long doctorId,
        @NonNull
        Long patientId
)
{}
