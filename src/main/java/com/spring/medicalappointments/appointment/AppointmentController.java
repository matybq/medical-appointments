package com.spring.medicalappointments.appointment;

import com.spring.medicalappointments.appointment.dto.AppointmentRequest;
import com.spring.medicalappointments.appointment.dto.AppointmentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path= "/api/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping(path = "/doctor/{doctorId}")
    public List<AppointmentResponse> getDoctorAppointments(@PathVariable Long doctorId) {
        return appointmentService.getDoctorAppointments(doctorId);
    }


    @GetMapping(path = "/patient/{patientId}")
    public List<AppointmentResponse> getPatientAppointments(@PathVariable Long patientId) {
        return appointmentService.getPatientAppointments(patientId);
    }


    // for doctor usage only. TSTATUS = ACCEPTED
    @PostMapping(path = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createAppointment(@Validated @RequestBody AppointmentRequest appointmentRequest) {
        appointmentService.createAppointment(appointmentRequest);
        return ResponseEntity.ok().body("Appointment CREATED SUCCESSFULLY");
    }

    // for patient usage only. MUST RECEIVE TSTATUS=WAITING
    @PostMapping(path = "/book")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> bookAppointment(@Validated @RequestBody AppointmentRequest appointmentRequest) {
        appointmentService.createAppointment(appointmentRequest);
        return ResponseEntity.ok().body("Appointment REQUESTED SUCCESSFULLY");
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateAppointment(@PathVariable Long id, @Validated @RequestBody AppointmentRequest appointmentRequest) {
        appointmentService.updateAppointment(id, appointmentRequest);
        return ResponseEntity.ok().body("Appointment "+id+" UPDATED SUCCESSFULLY: "+ appointmentRequest.date());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok().body("Appointment: " +id+ " DELETED SUCCESSFULLY");
    }

}
