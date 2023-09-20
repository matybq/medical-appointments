package com.spring.medicalappointments.appointment.dto;

import com.spring.medicalappointments.appointment.Appointment;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AppointmentDTOMapper implements Function<Appointment, AppointmentResponse> {

    @Override
    public AppointmentResponse apply(Appointment appointment) {
        return new AppointmentResponse(
                appointment.getId(),
                appointment.getDate(),
                appointment.getDetail(),
                appointment.getStatus(),
                appointment.getDoctor().getName(),
                appointment.getPatient().getName()
        );
    }

}