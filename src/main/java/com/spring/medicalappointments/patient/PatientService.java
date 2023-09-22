package com.spring.medicalappointments.patient;

import com.spring.medicalappointments.exception.ApiNotFoundException;
import com.spring.medicalappointments.exception.ApiRequestException;
import com.spring.medicalappointments.patient.dto.PatientDTOMapper;
import com.spring.medicalappointments.patient.dto.PatientRequest;
import com.spring.medicalappointments.patient.dto.PatientResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientDTOMapper patientDTOMapper;

    public PatientService(PatientRepository patientRepository, PatientDTOMapper patientDTOMapper) {
        this.patientRepository = patientRepository;
        this.patientDTOMapper = patientDTOMapper;
    }

    public List<PatientResponse> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(patientDTOMapper)
                .collect(Collectors.toList());
    }

    public PatientResponse getPatientById(Long id) {
        return patientRepository.findById(id)
                .map(patientDTOMapper)
                .orElseThrow(() -> new ApiNotFoundException("Patient: "+id+" not found"));
    }

    public List<PatientResponse> getAllPatientsByName(String name) {
        return patientRepository.findAllByNameContaining(name)
                .stream()
                .map(patientDTOMapper)
                .collect(Collectors.toList());
    }

    public void addPatient(PatientRequest patientRequest) {
        if (patientRepository.findByEmail(patientRequest.email()).isPresent()) {
            throw new ApiRequestException("A user with that email already exists. Try with another email");
        }

        String nameUpperCase = patientRequest.name().toUpperCase();
        Patient patient = new Patient(nameUpperCase, patientRequest.email(), patientRequest.password(), patientRequest.dob());
        patientRepository.save(patient);
    }

    @Transactional
    public void updatePatient(Long id, PatientRequest patientRequest) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ApiNotFoundException("Patient: "+id+" not found"));

        patient.setName(patientRequest.name());
        patient.setPassword(patientRequest.password());
        patient.setEmail(patientRequest.email());
        patient.setDob(patientRequest.dob());
        patientRepository.save(patient);
    }

    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new ApiNotFoundException("Patient: "+id+" not found");
        }
        patientRepository.deleteById(id);
    }

}
