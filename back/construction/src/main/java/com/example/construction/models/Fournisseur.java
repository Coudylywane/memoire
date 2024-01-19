package com.example.construction.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private int status = 0;

    // Méthode pour la suppression logique
    public void softDelete() {
        this.status = 1;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "categorie", referencedColumnName = "id")
    private CategorieFournisseur categorieFournisseur;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "id")
    private TypeFournisseur typeFournisseur;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "contact", referencedColumnName = "id")
    private ContactFournisseur contactFournisseur;
}
