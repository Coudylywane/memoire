package com.example.construction.controllers;

import java.util.List;

import javax.persistence.EntityNotFoundException;

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

import com.example.construction.models.UniteMesure;
import com.example.construction.models.ZoneStock;
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
            // Enregistrez le nouveau ZoneStock avec le statut défini sur 0 par défaut
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
}