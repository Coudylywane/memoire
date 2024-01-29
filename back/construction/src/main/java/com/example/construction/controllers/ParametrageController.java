package com.example.construction.controllers;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.example.construction.models.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.construction.request.TypeArticleRequest;
import com.example.construction.services.ParametrageService;

import lombok.RequiredArgsConstructor;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ParametrageController {

    private final ParametrageService parametrageService;

    @PostMapping("/zone")
    public ResponseEntity<ZoneStock> addZone(@RequestBody ZoneStock newZone) {
        try {
            // Enregistrez le nouveau ZoneStock avec le statut défini sur 0 par défaut
            newZone.setStatus(0);
            ZoneStock savedZone = parametrageService.addZone(newZone);

            return ResponseEntity.ok(savedZone);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
    

    @GetMapping("/zone")
    public ResponseEntity<?> getAllZone() {
        List<ZoneStock> zone = parametrageService.getAllZone();
        return ResponseEntity.ok(zone);
    }

    @PutMapping("/zone/{id}")
    public ResponseEntity<?> updateZone(@PathVariable Long id, @RequestBody ZoneStock updatedZone) {
        try {
            updatedZone.setId(id);
            updatedZone.setStatus(0);

            ZoneStock updated = parametrageService.updateZone(updatedZone);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Zone not found with id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    @DeleteMapping("/zone/{id}")
    public ResponseEntity<?> softDeleteZone(@PathVariable Long id) {
        try {
            parametrageService.softDeleteZone(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Zone not found with id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    ///////////////////////////////////// UNITE MESURE ///////////////////////////////////////////////////////////
    
    @PostMapping("/unite")
    public ResponseEntity<UniteMesure> addUnite(@RequestBody UniteMesure newUnite) {
        try {
            // Enregistrez le nouveau unite avec le statut défini sur 0 par défaut
            newUnite.setStatus(0);
            UniteMesure savedUnite = parametrageService.addUnite(newUnite);

            return ResponseEntity.ok(savedUnite);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
    

    @GetMapping("/unite")
    public ResponseEntity<?> getAllUnite() {
        List<UniteMesure> zone = parametrageService.getAllUnite();
        return ResponseEntity.ok(zone);
    }

    @PutMapping("/unite/{id}")
    public ResponseEntity<?> updateUnite(@PathVariable Long id, @RequestBody UniteMesure updatedUnite) {
        try {
            updatedUnite.setId(id);
            updatedUnite.setStatus(0);
            UniteMesure updated = parametrageService.updateUnite(updatedUnite);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unite not found with id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/unite/{id}")
    public ResponseEntity<?> softDeleteUnite(@PathVariable Long id) {
        try {
            parametrageService.softDeleteUnite(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unite not found with id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    ///////////////////////////////////// FAMILLE ///////////////////////////////////////////////////////////

    @PostMapping("/famille")
    public ResponseEntity<FamilleArticle> addFamille(@RequestBody FamilleArticle newFamille) {
        try {
            // Enregistrez le nouveau ZoneStock avec le statut défini sur 0 par défaut
            newFamille.setStatus(0);
            FamilleArticle savedFamille = parametrageService.addFamille(newFamille);

            return ResponseEntity.ok(savedFamille);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/famille")
    public ResponseEntity<?> getAllFamille() {
        List<FamilleArticle> famille = parametrageService.getAllFamilleWithTypes();
        return ResponseEntity.ok(famille);
    }

    @GetMapping("/familleAndType")
    public ResponseEntity<?> getAllFamilleWithTypes() {
        List<FamilleArticle> famille = parametrageService.getAllFamilleWithTypes();
        return ResponseEntity.ok(famille);
    }


    @PutMapping("/famille/{id}")
    public ResponseEntity<?> updateFamille(@PathVariable Long id, @RequestBody FamilleArticle famille) {
        try {
            famille.setId(id);
            famille.setStatus(0);
            FamilleArticle updated = parametrageService.updateFamille(famille);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Famille not found with id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/famille/{id}")
    public ResponseEntity<?> softDeleteFamille(@PathVariable Long id) {
        try {
            parametrageService.softDeleteFamille(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Famille not found with id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    ///////////////////////////////////// TYPE ARTICLE ///////////////////////////////////////////////////////////

//Ajout
    @PostMapping("/typeArticles")
    public ResponseEntity<TypeArticle> addTypeArticleToFamily(
            @RequestBody TypeArticleRequest typeArticleRequest) {
        TypeArticle addedTypeArticle = parametrageService.addTypeArticleToFamily(typeArticleRequest);
        return ResponseEntity.ok(addedTypeArticle);
    }

    ///////////////////////////////////// FOURNISSEURS ///////////////////////////////////////////////////////////

    @PostMapping("/fournisseur")
    public ResponseEntity<Fournisseur> addFournisseur(@RequestBody Fournisseur newFournisseur) {
        try {
            // Enregistrez le nouveau ZoneStock avec le statut défini sur 0 par défaut
            newFournisseur.setStatus(0);
            Fournisseur savedFournisseur = parametrageService.addFournisseur(newFournisseur);

            return ResponseEntity.ok(savedFournisseur);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/fournisseur")
    public ResponseEntity<?> getAllFournisseur() {
        List<Fournisseur> fournisseur = parametrageService.getAllFournisseurWithTypes();
        return ResponseEntity.ok(fournisseur);
    }

    @GetMapping("/fournisseurAndType")
    public ResponseEntity<?> getAllFournisseurWithTypes() {
        List<Fournisseur> fournisseur = parametrageService.getAllFournisseurWithTypes();
        return ResponseEntity.ok(fournisseur);
    }


    @PutMapping("/fournisseur/{id}")
    public ResponseEntity<?> updateFournisseur(@PathVariable Long id, @RequestBody Fournisseur fournisseur) {
        try {
            fournisseur.setId(id);
            fournisseur.setStatus(0);
            Fournisseur updated = parametrageService.updateFournisseur(fournisseur);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Famille not found with id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/fournisseur/{id}")
    public ResponseEntity<?> softDeleteFournisseur(@PathVariable Long id) {
        try {
            parametrageService.softDeleteFamille(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Famille not found with id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    //////////////////////////////////// TYPE PRESTATAIRE /////////////////////////

    @PostMapping("/typePre")
    public ResponseEntity<TypePrestataire> addTypePrestataire(@RequestBody TypePrestataire newTypePrestataire) {
        try {
            // Enregistrez le nouveau ZoneStock avec le statut défini sur 0 par défaut
            newTypePrestataire.setStatus(0);
            TypePrestataire savedTypePrestataire = parametrageService.addTypePrestataire(newTypePrestataire);

            return ResponseEntity.ok(savedTypePrestataire);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }


    @GetMapping("/typePre")
    public ResponseEntity<?> getAllTypePrestataire() {
        List<TypePrestataire> zone = parametrageService.getAllTypePrestataire();
        return ResponseEntity.ok(zone);
    }

    @PutMapping("/typePre/{id}")
    public ResponseEntity<?> updateTypePrestataire(@PathVariable Long id, @RequestBody TypePrestataire updatedTypePrestataire) {
        try {
            updatedTypePrestataire.setId(id);
            updatedTypePrestataire.setStatus(0);

            TypePrestataire updated = parametrageService.updateTypePrestataire(updatedTypePrestataire);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TypePrestataire not found with id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/typePre/{id}")
    public ResponseEntity<?> softDeleteTypePrestataire(@PathVariable Long id) {
        try {
            parametrageService.softDeleteTypePrestataire(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Type Prestataire not found with id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
