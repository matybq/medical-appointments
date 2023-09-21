package com.spring.medicalappointments.patient;

import com.spring.medicalappointments.patient.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path= "/api/v1/patients/")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<PatientResponse> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("id/{id}")
    public PatientResponse getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    @GetMapping("name/{name}")
    public PatientResponse getPatientByName(@PathVariable String name) {
        return patientService.getPatientByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addPatient(@Validated @RequestBody PatientRequest patientRequest) {
        patientService.addPatient(patientRequest);
        return ResponseEntity.ok().body("Patient: " + patientRequest.name() + " REGISTERED SUCCESSFULLY");
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updatePatient(@PathVariable Long id, @Validated @RequestBody PatientRequest patientRequest) {
        patientService.updatePatient(id, patientRequest);
        return ResponseEntity.ok().body("Patient: " +id+" , "+ patientRequest.name() + " UPDATED SUCCESSFULLY");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok().body("Patient:  "+id+" DELETED SUCCESSFULLY");
    }
}
