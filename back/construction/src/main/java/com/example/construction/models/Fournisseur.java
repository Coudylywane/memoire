package com.example.construction.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String adresse;
    private String telephone;
    private String email;
    private String totalVersement;
    private String solde;

    @ManyToOne
    @JoinColumn(name = "categorie", referencedColumnName = "id")
    private CategorieFournisseur categorieFournisseur;

    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "id")
    private TypeFournisseur typeFournisseur;
}
