package com.example.construction.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.construction.models.FamilleArticle;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilleArticleRepository extends JpaRepository<FamilleArticle , Long>{

        Optional<FamilleArticle> findByDesignation(String designation);

}
