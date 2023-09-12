package com.spring.medicalappointments.patient;

import com.spring.medicalappointments.patient.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path= "/api")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/patients/{id}")
    public Optional<PatientResponse> getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    @PostMapping("/addpatient")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addPatient(@Validated @RequestBody PatientRequest patientRequest) {
        patientService.addPatient(patientRequest);
        return ResponseEntity.ok().body("Patient: " + patientRequest.name() + " registered succesfully");
    }

    @PutMapping("/updatepatient/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable Long id, @RequestBody PatientRequest patientRequest) {
        patientService.updatePatient(id, patientRequest);
        return ResponseEntity.ok().body("Patient: " + patientRequest.name() + " UPDATED");
    }

    @DeleteMapping("/deletepatient/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Long id) {
        return patientService.getPatientById(id).map(p -> {
            patientService.deletePatient(id);
            return ResponseEntity.ok().body("Patient:  "+id+" DELETED");
        }).orElseThrow(() -> new NoSuchElementException("Patient: "+id+"NOT FOUND"));
    }
}
