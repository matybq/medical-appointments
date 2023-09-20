package com.spring.medicalappointments.doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByName(String name);

    boolean existsByUsername(String username);


    Optional<List<Doctor>> findAllBySpecialty(TSpecialty specialty);

}
