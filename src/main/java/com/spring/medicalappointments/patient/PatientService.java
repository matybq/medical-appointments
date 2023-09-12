package com.spring.medicalappointments.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public void addPatient(Patient patient) {
        if (patientRepository.findByEmail(patient.getEmail()).isPresent()) {
            throw new IllegalStateException("A user with that email already exists. Try with another email");
        }
        patientRepository.save(patient);
    }

    public void updatePatient(Long id, Patient patient) {

        Patient updatedPatient = getPatientById(id)
                .orElseThrow(() -> new NoSuchElementException("Patient" + id + "NOT FOUND"));
        updatedPatient.setName(patient.getName());
        updatedPatient.setEmail(patient.getEmail());
        updatedPatient.setDob(patient.getDob());
        patientRepository.save(updatedPatient);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
