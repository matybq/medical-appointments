package com.spring.medicalappointments.patient;

import com.spring.medicalappointments.patient.dto.PatientDTOMapper;
import com.spring.medicalappointments.patient.dto.PatientRequest;
import com.spring.medicalappointments.patient.dto.PatientResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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
                .orElseThrow(() -> new IllegalArgumentException("not found"));  // handle this exception propertly
    }

    public PatientResponse getPatientByName(String name) {
        return patientRepository.findByName(name)
                .map(patientDTOMapper)
                .orElseThrow(() -> new IllegalArgumentException("not found"));   // handle this exception propertly
    }

    public void addPatient(PatientRequest patientRequest) {
        if (patientRepository.findByEmail(patientRequest.email()).isPresent()) {
            throw new IllegalStateException("A user with that email already exists. Try with another email");
        }

        Patient patient = new Patient(patientRequest.name(), patientRequest.email(), patientRequest.password(), patientRequest.dob());
        patientRepository.save(patient);
    }

    public void updatePatient(Long id, PatientRequest patientRequest) {

        Patient updatedPatient = patientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Patient: " + id + "NOT FOUND"));

        updatedPatient.setName(patientRequest.name());
        updatedPatient.setPassword(patientRequest.password());
        updatedPatient.setEmail(patientRequest.email());
        updatedPatient.setDob(patientRequest.dob());
        patientRepository.save(updatedPatient);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

}
