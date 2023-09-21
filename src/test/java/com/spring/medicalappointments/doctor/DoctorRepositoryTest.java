package com.spring.medicalappointments.doctor;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepositoryUnderTest;

    @AfterEach
    void tearDown() {
        doctorRepositoryUnderTest.deleteAll();
    }

    @Test
    void testFindByName() {
        String username = "diego";
        Doctor doctor = new Doctor("diego", username, "password", TSpecialty.CIRUJANO);
        doctorRepositoryUnderTest.save(doctor);

        Optional<Doctor> expected = doctorRepositoryUnderTest.findByName(username);

        assertTrue(expected.isPresent());
    }

    @Test
    void testDoesntFindByName() {
        String username = "diego";

        Optional<Doctor> expected = doctorRepositoryUnderTest.findByName(username);

        assertFalse(expected.isPresent());
    }

    @Test
    void testExistsByUsername() {
        String username = "diego";
        Doctor doctor = new Doctor("diego", username, "password", TSpecialty.CIRUJANO);
        doctorRepositoryUnderTest.save(doctor);

        boolean expected = doctorRepositoryUnderTest.existsByUsername(username);

        assertTrue(expected);
    }

    @Test
    void testDoesntExistsByUsername() {
        String username = "diego";

        boolean expected = doctorRepositoryUnderTest.existsByUsername(username);

        assertFalse(expected);
    }

    @Test
    void testFindAllBySpecialty() {

    }
}