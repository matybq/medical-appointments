package com.spring.medicalappointments.patient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class PatientConfig {

    @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository) {
        return args -> {
            Patient jorge = new Patient(
                    "jorge",
                    "jorge@gmail.com",
                    LocalDate.of(2001, Month.MAY, 3)
            );

            Patient miguel = new Patient(
                    "Miguel",
                    "miguelito@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 5)
            );

            patientRepository.saveAll(
                    List.of(jorge,miguel)
            );
        };
    }
}
