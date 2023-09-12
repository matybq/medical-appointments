package com.spring.medicalappointments.patient;

import com.spring.medicalappointments.patient.dto.PatientRequest;
import com.spring.medicalappointments.patient.dto.PatientResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<PatientResponse> getPatientById(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent()) {
            Patient patientGetted = patient.get();
            PatientResponse patientResponse =
                    new PatientResponse(patientGetted.getName(),
                    patientGetted.getEmail(),
                    patientGetted.getDob());
            return Optional.of(patientResponse);
        }

        return Optional.empty();
    }

    public void addPatient(PatientRequest patientRequest) {
        if (patientRepository.findByEmail(patientRequest.email()).isPresent()) {
            throw new IllegalStateException("A user with that email already exists. Try with another email");
        }

        Patient patient = new Patient(patientRequest.name(), patientRequest.email(), patientRequest.dob());
        patientRepository.save(patient);
    }

    public void updatePatient(Long id, PatientRequest patientRequest) {

        Patient updatedPatient = patientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Patient: " + id + "NOT FOUND"));

        updatedPatient.setName(patientRequest.name());
        updatedPatient.setEmail(patientRequest.email());
        updatedPatient.setDob(patientRequest.dob());
        patientRepository.save(updatedPatient);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
