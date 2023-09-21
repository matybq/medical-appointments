package com.spring.medicalappointments.appointment;

import com.spring.medicalappointments.doctor.Doctor;
import com.spring.medicalappointments.patient.Patient;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime date;
    private String detail;
    private TStatus status;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Appointment(LocalDateTime date, String detail, TStatus status, Doctor doctor, Patient patient) {
        this.date = date;
        this.detail = detail;
        this.status = status;
        this.doctor = doctor;
        this.patient = patient;
    }


    public Appointment(LocalDateTime date, TStatus status) {
        this.date = date;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date.toLocalDate();
    }

    public LocalDateTime getDateAndHour() {
        return date;
    }

    public void setDateAndHour(LocalDateTime date) {
        this.date = date;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public TStatus getStatus() {
        return status;
    }

    public void setStatus(TStatus status) {
        this.status = status;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", date=" + date +
                ", detail='" + detail + '\'' +
                ", status=" + status +
                ", doctor=" + doctor +
                ", patient=" + patient +
                '}';
    }
}
