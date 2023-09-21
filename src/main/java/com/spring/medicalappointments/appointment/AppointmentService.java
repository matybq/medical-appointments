package com.spring.medicalappointments.appointment;


import com.spring.medicalappointments.appointment.dto.AppointmentDTOMapper;
import com.spring.medicalappointments.appointment.dto.AppointmentRequest;
import com.spring.medicalappointments.appointment.dto.AppointmentResponse;
import com.spring.medicalappointments.doctor.Doctor;
import com.spring.medicalappointments.doctor.DoctorRepository;
import com.spring.medicalappointments.exception.ApiNotFoundException;
import com.spring.medicalappointments.exception.ApiRequestException;
import com.spring.medicalappointments.patient.Patient;
import com.spring.medicalappointments.patient.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppointmentDTOMapper appointmentDTOMapper;

    public AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, AppointmentDTOMapper appointmentDTOMapper) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.appointmentDTOMapper = appointmentDTOMapper;
    }


    public List<AppointmentResponse> getDoctorAppointments(Long doctorId) {
        if (!doctorRepository.existsById(doctorId)) {
            throw new ApiNotFoundException("Doctor: " + doctorId + " not found");
        }

        return appointmentRepository.findAllByDoctorId(doctorId)
                .stream()
                .map(appointmentDTOMapper)
                .collect(Collectors.toList());
    }

    public List<AppointmentResponse> getPatientAppointments(Long patientId) {
        if (!patientRepository.existsById(patientId)) {
            throw new ApiNotFoundException("Patient: " + patientId + " not found");
        }

        return appointmentRepository.findAllByPatientId(patientId)
                .stream()
                .map(appointmentDTOMapper)
                .collect(Collectors.toList());
    }

    public List<AppointmentResponse> getAppointmentsByDoctorAndDate(Long doctorId, LocalDate date) {
        if (!doctorRepository.existsById(doctorId)) {
            throw new ApiNotFoundException("Doctor: " + doctorId + " not found");
        }

        return appointmentRepository.findAllByDoctorIdAndDate(doctorId, date)
                .stream()
                .map(appointmentDTOMapper)
                .collect(Collectors.toList());
    }

    public List<AppointmentResponse> getAppointmentsByPatientAndDate(Long patientId, LocalDate date) {
        if (!patientRepository.existsById(patientId)) {
            throw new ApiNotFoundException("Patient: " + patientId + " not found");
        }

        return appointmentRepository.findAllByPatientIdAndDate(patientId, date)
                .stream()
                .map(appointmentDTOMapper)
                .collect(Collectors.toList());
    }

    public void createAppointment(AppointmentRequest appointmentRequest) {
        Long patientId = appointmentRequest.patientId();
        Long doctorId = appointmentRequest.doctorId();
        LocalDateTime date = appointmentRequest.date();

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ApiNotFoundException("Patient: "+ patientId +" not found"));

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ApiNotFoundException("Doctor: "+ doctorId +" not found"));

        if (existsByDoctorIdAndDateTime(doctorId, date)) {
            throw new ApiRequestException("Doctor: " +doctorId+ " already have an appointment on: " + date);
        }

        Appointment appointment = new Appointment(appointmentRequest.date(),
                appointmentRequest.detail(), appointmentRequest.status(), doctor, patient);

        appointmentRepository.save(appointment);
    }

    // cant update doctor or patient
    public void updateAppointment(Long id, AppointmentRequest appointmentRequest) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ApiNotFoundException("Appointment: " + id + "NOT FOUND"));

        Long doctorId = appointmentRequest.doctorId();
        LocalDateTime date = appointmentRequest.date();

        if (doctorRepository.existsById(doctorId)) {
            throw new ApiNotFoundException("Doctor: " +doctorId+ " doesnt exist");
        }

        if (existsByDoctorIdAndDateTime(doctorId, date)) {
            throw new ApiRequestException("Doctor: " +doctorId+ " already have an appointment on: " + date);
        }

        appointment.setDateAndHour(date);
        appointment.setDetail(appointmentRequest.detail());
        appointment.setStatus(appointmentRequest.status());

        appointmentRepository.save(appointment);
    }


    public void deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new ApiNotFoundException("Appointment: "+id+" not found");
        }
        appointmentRepository.deleteById(id);
    }

    private boolean existsByDoctorIdAndDateTime(Long doctorId, LocalDateTime localDateTime) {
        return appointmentRepository.existsByDoctorIdAndDateTime(doctorId, localDateTime);
    }

}
