package com.spring.medicalappointments.appointment.dto;

import com.spring.medicalappointments.appointment.TStatus;
import lombok.NonNull;

import java.util.Date;

public record AppointmentRequest(
        @NonNull
        Date date,
        String detail,
        @NonNull
        TStatus status,
        @NonNull
        Long doctorId,
        @NonNull
        Long patientId
)
{}
