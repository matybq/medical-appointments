package com.spring.medicalappointments.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path= "/api")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/patients/{id}")
    public Optional<Patient> getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    @PostMapping("/addpatient")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addPatient(@RequestBody Patient patient) {
        patientService.addPatient(patient);
        return ResponseEntity.ok().body("Patient: " + patient.getName() + " registered succesfully");
    }

    @PutMapping("/updatepatient/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        patientService.updatePatient(id, patient);
        return ResponseEntity.ok().body("Patient: " + patient.getName() + " UPDATED");
    }

    @DeleteMapping("/deletepatient/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Long id) {
        return patientService.getPatientById(id).map(p -> {
            patientService.deletePatient(id);
            return ResponseEntity.ok().body("Patient:  "+id+" DELETED");
        }).orElseThrow(() -> new NoSuchElementException("Patient: "+id+"NOT FOUND"));
    }
}
