package com.spring.medicalappointments.doctor;

import com.spring.medicalappointments.doctor.dto.DoctorRequest;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void addDoctor(DoctorRequest doctorRequest) {
        if (doctorRepository.existsByUsername(doctorRequest.username())) {
            throw new IllegalArgumentException("username already used");
        }

        Doctor doctor = new Doctor(doctorRequest.name(), doctorRequest.username(),
                doctorRequest.password(), doctorRequest.specialty());

        doctorRepository.save(doctor);
    }
}
