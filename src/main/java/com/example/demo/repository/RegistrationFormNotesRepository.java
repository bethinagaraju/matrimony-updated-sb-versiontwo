package com.example.demo.repository;

import com.example.demo.entity.RegistrationFormNotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationFormNotesRepository extends JpaRepository<RegistrationFormNotes, Long> {

    @Query("SELECT n FROM RegistrationFormNotes n WHERE n.registrationForm.id = :registrationFormId")
    List<RegistrationFormNotes> findByRegistrationFormId(@Param("registrationFormId") Long registrationFormId);
}
