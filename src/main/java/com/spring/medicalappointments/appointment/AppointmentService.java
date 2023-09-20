package com.spring.medicalappointments.appointment;


import com.spring.medicalappointments.appointment.dto.AppointmentDTOMapper;
import com.spring.medicalappointments.appointment.dto.AppointmentRequest;
import com.spring.medicalappointments.appointment.dto.AppointmentResponse;
import com.spring.medicalappointments.doctor.Doctor;
import com.spring.medicalappointments.doctor.DoctorRepository;
import com.spring.medicalappointments.patient.Patient;
import com.spring.medicalappointments.patient.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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
            throw new NoSuchElementException("Doctor: " +doctorId+ " not found");
        }

        return appointmentRepository.findAllByDoctorId(doctorId).stream()
                .map(appointmentDTOMapper)
                .collect(Collectors.toList());
    }


    public List<AppointmentResponse> getPatientAppointments(Long patientId) {
        if (!patientRepository.existsById(patientId)) {
            throw new NoSuchElementException("Patient: " +patientId+ " not found");
        }

        return appointmentRepository.findAllByPatientId(patientId).stream()
                .map(appointmentDTOMapper)
                .collect(Collectors.toList());
    }


    public void createAppointment(AppointmentRequest appointmentRequest) {

        /* checkear que no se pueda crear un appointment para un medico si ya tiene uno a esa hora :
                List<Appointment> doctorAppointments = appointmentRepository.findAllByDoctorId(appointmentRequest.doctorId());
        if (doctorAppointments.contains())*/

        Patient patient = patientRepository.findById(appointmentRequest.patientId())
                .orElseThrow(() -> new NoSuchElementException("Patient not found"));

        Doctor doctor = doctorRepository.findById(appointmentRequest.doctorId())
                .orElseThrow(() -> new NoSuchElementException("Doctor not found"));


        Appointment appointment = new Appointment(appointmentRequest.date(),
                appointmentRequest.detail(), appointmentRequest.status(), doctor, patient);

        appointmentRepository.save(appointment);
    }


    public void updateAppointment(Long id, AppointmentRequest appointmentRequest) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Appointment: " + id + "NOT FOUND"));

        appointment.setDate(appointmentRequest.date());
        appointment.setDetail(appointmentRequest.detail());
        appointment.setStatus(appointmentRequest.status());

        appointmentRepository.save(appointment);
    }


    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

}
