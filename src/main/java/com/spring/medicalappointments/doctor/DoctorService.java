package com.spring.medicalappointments.doctor;

import com.spring.medicalappointments.doctor.dto.DoctorDTOMapper;
import com.spring.medicalappointments.doctor.dto.DoctorRequest;
import com.spring.medicalappointments.doctor.dto.DoctorResponse;
import com.spring.medicalappointments.exception.ApiNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorDTOMapper doctorDTOMapper;
    public DoctorService(DoctorRepository doctorRepository, DoctorDTOMapper doctorDTOMapper) {
        this.doctorRepository = doctorRepository;
        this.doctorDTOMapper = doctorDTOMapper;
    }

    public void addDoctor(DoctorRequest doctorRequest) {
        if (doctorRepository.existsByUsername(doctorRequest.username())) {
            throw new IllegalArgumentException("username already used");
        }
        String nameUpperCase = doctorRequest.name().toUpperCase();
        Doctor doctor = new Doctor(nameUpperCase, doctorRequest.username(),
                doctorRequest.password(), doctorRequest.specialty());

        doctorRepository.save(doctor);
    }

    public List<DoctorResponse> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(doctorDTOMapper)
                .collect(Collectors.toList());
    }

    public DoctorResponse getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .map(doctorDTOMapper)
                .orElseThrow(() -> new ApiNotFoundException("Doctor: "+id+" not found"));
    }

    public List<DoctorResponse> getAllDoctorsByName(String name) {    // should return List of Doctors
        return doctorRepository.findAllByNameContaining(name)
                .stream()
                .map(doctorDTOMapper)
                .collect(Collectors.toList());
    }

    public List<DoctorResponse> getAllDoctorsBySpecialty(TSpecialty specialty) {
        return doctorRepository.findAllBySpecialty(specialty)
                .stream()
                .map(doctorDTOMapper)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateDoctor(Long id, DoctorRequest doctorRequest) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ApiNotFoundException("Doctor: "+id+" not found"));

        doctor.setName(doctorRequest.name());
        doctor.setUsername(doctorRequest.username());
        doctor.setPassword(doctorRequest.password());
        doctor.setSpecialty(doctorRequest.specialty());
    }

    public void deleteDoctor(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new ApiNotFoundException("Doctor: "+id+" not found");
        }
        doctorRepository.deleteById(id);
    }
}
