package com.example.construction.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactPrestataire extends JpaRepository<ContactPrestataire, Long> {
}
