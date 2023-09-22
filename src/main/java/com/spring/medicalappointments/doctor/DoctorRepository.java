package com.spring.medicalappointments.doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByName(String name);
    boolean existsByUsername(String username);
    List<Doctor> findAllBySpecialty(TSpecialty specialty);
    List<Doctor> findAllByNameContaining(String name);
}
