package com.spring.medicalappointments.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllByDoctorId(Long doctorId);

    List<Appointment> findAllByPatientId(Long patientId);

    // only filters by DATE
    @Query("SELECT a FROM Appointment a " +
            "WHERE a.doctor.id = :doctorId " +
            "AND FUNCTION('DATE', a.date) = :date")
    List<Appointment> findAllByDoctorIdAndDate(@Param("doctorId") Long doctorId, @Param("date") LocalDate date);

    // filters by DATE & TIME
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Appointment a " +
            "WHERE a.doctor.id = :doctorId " +
            "AND a.date = :dateTime")
    boolean existsByDoctorIdAndDateTime(@Param("doctorId") Long doctorId, @Param("dateTime") LocalDateTime dateTime);


    // only filters by DATE
    @Query("SELECT a FROM Appointment a " +
            "WHERE a.patient.id = :patientId " +
            "AND FUNCTION('DATE', a.date) = :date")
    List<Appointment> findAllByPatientIdAndDate(@Param("patientId") Long patientId, @Param("date") LocalDate date);
}
