package com.spring.medicalappointments.doctor;

import com.spring.medicalappointments.doctor.dto.DoctorRequest;
import com.spring.medicalappointments.doctor.dto.DoctorResponse;
import com.spring.medicalappointments.patient.dto.PatientResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path= "/api/v1/doctors/")
public class DoctorController {

    private final DoctorService doctorService;
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public List<DoctorResponse> findAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("id/{id}")
    public DoctorResponse findDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }

    @GetMapping("name")
    public List<DoctorResponse> findAllDoctorsByName(@RequestParam String name) {
        String nameToFind = name.toUpperCase().replace("-", " ");
        return doctorService.getAllDoctorsByName(nameToFind);
    }

    @GetMapping("specialty/{specialty}")
    public List<DoctorResponse> findDoctorsBySpecialty(@PathVariable TSpecialty specialty) {
        return doctorService.getAllDoctorsBySpecialty(specialty);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addDoctor(@Validated @RequestBody DoctorRequest doctorRequest) {
        doctorService.addDoctor(doctorRequest);
        return ResponseEntity.ok().body("Doctor: " + doctorRequest.name() + " registered SUCCESSFULLY");
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable Long id, @RequestBody DoctorRequest doctorRequest) {
        doctorService.updateDoctor(id, doctorRequest);
        return ResponseEntity.ok().body("Doctor: " + doctorRequest.name() + " updated SUCCESSFULLY");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok().body("Doctor:  "+id+" DELETED SUCCESSFULLY");
    }
}
