package com.spring.medicalappointments.appointment;

import com.spring.medicalappointments.appointment.dto.AppointmentRequest;
import com.spring.medicalappointments.appointment.dto.AppointmentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path= "/api/v1/appointments/")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping(path = "doctor/{doctorId}")
    public List<AppointmentResponse> findAppointmentsByDoctor(@PathVariable Long doctorId) {
        return appointmentService.getDoctorAppointments(doctorId);
    }

    @GetMapping(path = "patient/{patientId}")
    public List<AppointmentResponse> findAppointmentsByPatient(@PathVariable Long patientId) {
        return appointmentService.getPatientAppointments(patientId);
    }

    @GetMapping(path = "doctor/{doctorId}/date-appointments")
    public List<AppointmentResponse> findAppointmentsByDoctorAndDate(@PathVariable Long doctorId, @RequestParam LocalDate date) {
        return appointmentService.getAppointmentsByDoctorAndDate(doctorId, date);
    }

    @GetMapping(path = "doctor/{doctorId}/today-appointments")
    public List<AppointmentResponse> findTodayAppointmentsByDoctor(@PathVariable Long doctorId)  {
        LocalDate today = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return appointmentService.getAppointmentsByDoctorAndDate(doctorId, today);
    }

    @GetMapping(path = "patient/{patientId}/date-appointments")
    public List<AppointmentResponse> findAppointmentsByPatientAndDate(@PathVariable Long patientId, @RequestParam LocalDate date) {
        return appointmentService.getAppointmentsByPatientAndDate(patientId, date);
    }

    @GetMapping(path = "patient/{patientId}/today-appointments")
    public List<AppointmentResponse> findTodayAppointmentsByPatient(@PathVariable Long patientId)  {
        LocalDate today = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return appointmentService.getAppointmentsByPatientAndDate(patientId, today);
    }

    // for doctor usage only. TSTATUS = ACCEPTED
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createAppointment(@Validated @RequestBody AppointmentRequest appointmentRequest) {
        appointmentService.createAppointment(appointmentRequest);
        return ResponseEntity.ok().body("Appointment CREATED SUCCESSFULLY");
    }

    // for patient usage only. MUST RECEIVE TSTATUS=WAITING
    @PostMapping(path = "book")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> bookAppointment(@Validated @RequestBody AppointmentRequest appointmentRequest) {
        appointmentService.createAppointment(appointmentRequest);
        return ResponseEntity.ok().body("Appointment REQUESTED SUCCESSFULLY");
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?> updateAppointment(@PathVariable Long id, @Validated @RequestBody AppointmentRequest appointmentRequest) {
        appointmentService.updateAppointment(id, appointmentRequest);
        return ResponseEntity.ok().body("Appointment "+id+" UPDATED SUCCESSFULLY: "+ appointmentRequest.date());
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok().body("Appointment: " +id+ " DELETED SUCCESSFULLY");
    }

}
