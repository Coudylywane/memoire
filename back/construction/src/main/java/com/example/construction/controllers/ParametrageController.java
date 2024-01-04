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

////////////////////////////////////// CATEGORIE FOURNISSEUR ///////////////////////////////////////////////////////

    @PostMapping("/categorie-fournisseur")
    public ResponseEntity<CategorieFournisseur> addCategorieFournisseur(@RequestBody CategorieFournisseur newCategorieFournisseur) {
        try {
            // Enregistrez le nouveau CATEGORIE FOURNISSEUR avec le statut défini sur 0 par défaut
            newCategorieFournisseur.setStatus(0);
            CategorieFournisseur savedCategorieFournisseur = parametrageService.addCategorieFournisseur(newCategorieFournisseur);

            return ResponseEntity.ok(savedCategorieFournisseur);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }


    @GetMapping("/categorie-fournisseur")
    public ResponseEntity<?> getAllCategorieFournisseur() {
        List<CategorieFournisseur> categories = parametrageService.getAllCategorieFournisseur();
        return ResponseEntity.ok(categories);
    }

    @PutMapping("/categorie-fournisseur/{id}")
    public ResponseEntity<?> updateCategorieFournisseur(@PathVariable Long id, @RequestBody CategorieFournisseur updatedCategorieFournisseur) {
        try {
            updatedCategorieFournisseur.setId(id);
            updatedCategorieFournisseur.setStatus(0);

            CategorieFournisseur updated = parametrageService.updateCategorieFournisseur(updatedCategorieFournisseur);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Zone not found with id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/categorie-fournisseur/{id}")
    public ResponseEntity<?> softDeleteCategorieFournisseur(@PathVariable Long id) {
        try {
            parametrageService.softDeleteCategorieFournisseur(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Zone not found with id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    ////////////////////////////////////// TYPE FOURNISSEUR ///////////////////////////////////////////////////////

    @PostMapping("/type-fournisseur")
    public ResponseEntity<TypeFournisseur> addTypeFournisseur(@RequestBody TypeFournisseur newCategorieFournisseur) {
        try {
            // Enregistrez le nouveau TYPE FOURNISSEUR avec le statut défini sur 0 par défaut
            newCategorieFournisseur.setStatus(0);
            TypeFournisseur savedCategorieFournisseur = parametrageService.addTypeFournisseur(newCategorieFournisseur);
            return ResponseEntity.ok(savedCategorieFournisseur);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/type-fournisseur")
    public ResponseEntity<?> getAllTypeFournisseur() {
        List<TypeFournisseur> categories = parametrageService.getAllTypeFournisseur();
        return ResponseEntity.ok(categories);
    }

    @PutMapping("/type-fournisseur/{id}")
    public ResponseEntity<?> updateTypeFournisseur(@PathVariable Long id, @RequestBody TypeFournisseur updatedTypeFournisseur) {
        try {
            updatedTypeFournisseur.setId(id);
            updatedTypeFournisseur.setStatus(0);
            TypeFournisseur updated = parametrageService.updateTypeFournisseur(updatedTypeFournisseur);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TypeFournisseur not found with id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/type-fournisseur/{id}")
    public ResponseEntity<?> softDeleteTypeFournisseur(@PathVariable Long id) {
        try {
            parametrageService.softDeleteTypeFournisseur(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TypeFournisseur not found with id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    ////////////////////////////////////// CONTACT FOURNISSEUR ///////////////////////////////////////////////////////

    @PostMapping("/contact-fournisseur")
    public ResponseEntity<ContactFournisseur> addContactFournisseur(@RequestBody ContactFournisseur newContactFournisseur) {
        try {
            // Enregistrez le nouveau CONTACT FOURNISSEUR avec le statut défini sur 0 par défaut
            newContactFournisseur.setStatus(0);
            ContactFournisseur savedCategorieFournisseur = parametrageService.createContactFournisseur(newContactFournisseur);
            return ResponseEntity.ok(savedCategorieFournisseur);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/contact-fournisseur")
    public ResponseEntity<?> getAllContactFournisseur() {
        List<ContactFournisseur> contacts = parametrageService.getAllContactFournisseur();
        return ResponseEntity.ok(contacts);
    }

    @PutMapping("/contact-fournisseur/{id}")
    public ResponseEntity<?> updateContactFournisseur(@PathVariable Long id, @RequestBody ContactFournisseur updatedContactFournisseur) {
        try {
            updatedContactFournisseur.setId(id);
            updatedContactFournisseur.setStatus(0);
            ContactFournisseur updated = parametrageService.updateContactFournisseur(updatedContactFournisseur);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ContactFournisseur not found with id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/contact-fournisseur/{id}")
    public ResponseEntity<?> softDeleteContactFournisseur(@PathVariable Long id) {
        try {
            parametrageService.softDeleteContactFournisseur(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ContactFournisseur not found with id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
