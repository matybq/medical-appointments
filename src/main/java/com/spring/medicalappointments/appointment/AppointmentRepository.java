package com.spring.medicalappointments.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllByDoctorId(Long doctorId);

    List<Appointment> findAllByPatientId(Long patientId);
}
