package com.spring.medicalappointments.doctor;

import com.spring.medicalappointments.appointment.Appointment;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    private String name;
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    private String password;
    private TSpecialty specialty;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    public Doctor(String name, String username, String password, TSpecialty specialty) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.specialty = specialty;
    }

    public Doctor(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.specialty = TSpecialty.CLINICO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TSpecialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(TSpecialty specialty) {
        this.specialty = specialty;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", specialty=" + specialty +
                '}';
    }
}
