package com.example.construction.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.construction.models.Role;
import java.util.List;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByLibelle(String libelle);
} 
