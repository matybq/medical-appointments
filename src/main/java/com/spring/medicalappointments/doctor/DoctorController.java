package com.spring.medicalappointments.doctor;

import com.spring.medicalappointments.doctor.dto.DoctorRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path= "/api/doctor")
public class DoctorController {

    private final DoctorService doctorService;
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addDoctor(@Validated @RequestBody DoctorRequest doctorRequest) {
        doctorService.addDoctor(doctorRequest);
        return ResponseEntity.ok().body("Doctor: " + doctorRequest.name() + " registered SUCCESSFULLY");
    }

}
