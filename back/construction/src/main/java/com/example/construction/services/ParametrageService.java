package com.example.construction.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.construction.models.*;
import com.example.construction.repositories.*;
import com.example.construction.request.TypeArticleRequest;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ParametrageService {
    @PersistenceContext
    private EntityManager entityManager;

    private final ZoneStockRepository zoneStockRepository;
    private final UniteMesureRepository uniteMesureRepository;
    private final FamilleArticleRepository familleArticleRepository;
    private final CategorieFournisseurRepository categorieFournisseurRepository;
    private final ContactFournisseurRepository contactFournistRepository;
    private final FournisseurRepository fournisseurRepository;
    private final ReglementFournisseurRepository reglementFournisseurRepository;
    private final TypeFournisseurRepository typeFournisseurRepository;

    /// / //////////////////// ZONE //////////////////////////////////////////////////////////
    //AJOUT
    public ZoneStock addZone(ZoneStock zone) {
        try {
            zoneStockRepository.save(zone);
            return zone;
        } catch (Exception e) {
            throw e;
        }
    }

    //MODIFICATION
    public ZoneStock updateZone(ZoneStock updatedZone) {
        try {
            // Vérifier si la zone que vous souhaitez mettre à jour existe dans la base de données
            ZoneStock existingZone = zoneStockRepository.findById(updatedZone.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Zone not found with id: " + updatedZone.getId()));

            // Mettre à jour les propriétés de la zone existante avec les nouvelles valeurs
            existingZone.setDesignation(updatedZone.getDesignation());
            existingZone.setDescription(updatedZone.getDescription());

            // Enregistrer la mise à jour dans la base de données
            zoneStockRepository.save(existingZone);

            // Retourner la zone mise à jour
            return existingZone;
        } catch (Exception e) {
            // Gérer les exceptions, vous pouvez choisir de les logger ou de les relancer
            throw e;
        }
    }

    //Suppression
    public void softDeleteZone(Long zoneId) {
        ZoneStock existingZone = zoneStockRepository.findById(zoneId)
                .orElseThrow(() -> new EntityNotFoundException("Zone not found with id: " + zoneId));

        existingZone.softDelete(); // Utilisez la méthode de suppression logique définie dans l'entité
        zoneStockRepository.save(existingZone);
    }

    //LISTE
    public List<ZoneStock> getAllZone() {
        return zoneStockRepository.findAll();
    }

    /// / //////////////////// UNITE MESURE //////////////////////////////////////////////////////////

    //AJOUT
    public UniteMesure addUnite(UniteMesure uniteMesure) {
        try {
            uniteMesureRepository.save(uniteMesure);
            return uniteMesure;
        } catch (Exception e) {
            throw e;
        }
    }

    //MODIFICATION
    public UniteMesure updateUnite(UniteMesure uniteMesure) {
        try {
            // Vérifier si la zone que vous souhaitez mettre à jour existe dans la base de données
            UniteMesure existingUnite = uniteMesureRepository.findById(uniteMesure.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Unite not found with id: " + uniteMesure.getId()));

            // Mettre à jour les propriétés de la zone existante avec les nouvelles valeurs
            existingUnite.setNom(uniteMesure.getNom());
            existingUnite.setAbreviation(uniteMesure.getAbreviation());

            // Enregistrer la mise à jour dans la base de données
            uniteMesureRepository.save(existingUnite);

            // Retourner la zone mise à jour
            return existingUnite;
        } catch (Exception e) {
            // Gérer les exceptions, vous pouvez choisir de les logger ou de les relancer
            throw e;
        }
    }

    //Suppression
    public UniteMesure softDeleteUnite(Long uniteId) {
        UniteMesure existingUnite = uniteMesureRepository.findById(uniteId)
                .orElseThrow(() -> new EntityNotFoundException("Unite not found with id: " + uniteId));

        existingUnite.softDelete(); // Utilisez la méthode de suppression logique définie dans l'entité
        uniteMesureRepository.save(existingUnite);
        return existingUnite == null ? null : existingUnite;
    }

    //LISTE
    public List<UniteMesure> getAllUnite() {
        return uniteMesureRepository.findAll();
    }

    /// / //////////////////// FAMILLE  //////////////////////////////////////////////////////////

    //AJOUT
    public FamilleArticle addFamille(FamilleArticle famille) {
        try {
            familleArticleRepository.save(famille);
            return famille;
        } catch (Exception e) {
            throw e;
        }
    }

    //MODIFICATION
    // public FamilleArticle updateFamille(FamilleArticle famille) {
    //     try {
    //         // Vérifier si la famille que vous souhaitez mettre à jour existe dans la base de données
    //         FamilleArticle existingFamille = familleArticleRepository.findById(famille.getId())
    //                 .orElseThrow(() -> new EntityNotFoundException("Unite not found with id: " + famille.getId()));

    //         // Mettre à jour les propriétés de la zone existante avec les nouvelles valeurs
    //         existingFamille.setDesignation(famille.getDesignation());
    //         existingFamille.setDescription(famille.getDescription());

    //         // Enregistrer la mise à jour dans la base de données
    //         familleArticleRepository.save(existingFamille);

    //         // Retourner la zone mise à jour
    //         return existingFamille;
    //     } catch (Exception e) {
    //         // Gérer les exceptions, vous pouvez choisir de les logger ou de les relancer
    //         throw e;
    //     }
    // }

    @Transactional
    public FamilleArticle updateFamille(FamilleArticle famille) {
        try {
            // Vérifier si la famille que vous souhaitez mettre à jour existe dans la base de données
            FamilleArticle existingFamille = familleArticleRepository.findById(famille.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Famille not found with id: " + famille.getId()));

            // Mettre à jour les propriétés de la famille existante avec les nouvelles valeurs
            existingFamille.setDesignation(famille.getDesignation());
            existingFamille.setDescription(famille.getDescription());

            // Liste pour stocker les ArticleType à retirer
            List<TypeArticle> typesToRemove = new ArrayList<>();

            // Parcourir les ArticleType existants de la famille
            for (TypeArticle existingType : existingFamille.getTypeArticles()) {
                // Si l'ArticleType existant n'est pas présent dans la nouvelle liste, ajouter à la liste à retirer
                if (!famille.getTypeArticles().contains(existingType)) {
                    typesToRemove.add(existingType);
                }
            }
            // Supprimer les ArticleType à retirer
            for (TypeArticle typeToRemove : typesToRemove) {
                existingFamille.removeArticleType(typeToRemove);
            }
            // Enregistrer la mise à jour dans la base de données
            familleArticleRepository.save(existingFamille);

            // Retourner la famille mise à jour
            return existingFamille;
        } catch (Exception e) {
            // Gérer les exceptions, vous pouvez choisir de les logger ou de les relancer
            throw e;
        }
    }

    //Suppression
    public FamilleArticle softDeleteFamille(Long familleId) {
        FamilleArticle existingFamille = familleArticleRepository.findById(familleId)
                .orElseThrow(() -> new EntityNotFoundException("Famille not found with id: " + familleId));

        existingFamille.softDelete(); // Utilisez la méthode de suppression logique définie dans l'entité
        familleArticleRepository.save(existingFamille);
        return existingFamille == null ? null : existingFamille;
    }

    //LISTE
    public List<FamilleArticle> getAllFamille() {
        return familleArticleRepository.findAll();
    }

    //LISTE + TYPE
    @Transactional()
    public List<FamilleArticle> getAllFamilleWithTypes() {
        List<FamilleArticle> familles = familleArticleRepository.findAll();

        return familles.stream()
                .map(famille -> new FamilleArticle(
                        famille.getId(),
                        famille.getDesignation(),
                        famille.getDescription(),
                        famille.getStatus(),
                        famille.getTypeArticles()))
                .collect(Collectors.toList());
    }

    /// / //////////////////// TYPE ARTICLE //////////////////////////////////////////////////////////
    //AJOUT
    // public TypeArticle addTypeArticle(TypeArticle type) {
    //     try {
    //         typeArticleRepository.save(type);
    //         return type;
    //     } catch (Exception e) {
    //         throw e;
    //     }
    // }

    public TypeArticle addTypeArticleToFamily(TypeArticleRequest typeArticleRequest) {
        FamilleArticle famille = familleArticleRepository.findByDesignation(typeArticleRequest.getFamilleDesignation())
                .orElseGet(() -> {
                    FamilleArticle newFamille = new FamilleArticle();
                    newFamille.setDesignation(typeArticleRequest.getFamilleDesignation());
                    return familleArticleRepository.save(newFamille);
                });

        TypeArticle typeArticle = new TypeArticle();
        typeArticle.setDesignation(typeArticleRequest.getTypeDesignation());
        typeArticle.setDescription(typeArticleRequest.getTypeDescription());

        famille.addTypeArticle(typeArticle);
        familleArticleRepository.save(famille);

        return typeArticle;
    }


    /// / //////////////////// CATEGORIE FOURNISSEUR //////////////////////////////////////////////////////////
    //AJOUT
    public CategorieFournisseur addCategorieFournisseur(CategorieFournisseur categorieFournisseur) {
        try {
            categorieFournisseurRepository.save(categorieFournisseur);
            return categorieFournisseur;
        } catch (Exception e) {
            throw e;
        }
    }

    //MODIFICATION
    public CategorieFournisseur updateCategorieFournisseur(CategorieFournisseur updatedCategorieFournisseur) {
        try {
            // Vérifier si la zone que vous souhaitez mettre à jour existe dans la base de données
            CategorieFournisseur existingCategorieFournisseur = categorieFournisseurRepository.findById(updatedCategorieFournisseur.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Zone not found with id: " + updatedCategorieFournisseur.getId()));

            // Mettre à jour les propriétés de la zone existante avec les nouvelles valeurs
            existingCategorieFournisseur.setDesignation(updatedCategorieFournisseur.getDesignation());
            existingCategorieFournisseur.setDescription(updatedCategorieFournisseur.getDescription());

            // Enregistrer la mise à jour dans la base de données
            categorieFournisseurRepository.save(existingCategorieFournisseur);

            // Retourner la zone mise à jour
            return existingCategorieFournisseur;
        } catch (Exception e) {
            // Gérer les exceptions, vous pouvez choisir de les logger ou de les relancer
            throw e;
        }
    }

    //Suppression
    public void softDeleteCategorieFournisseur(Long categorieFournisseurId) {
        CategorieFournisseur existingCategorieFournisseur = categorieFournisseurRepository.findById(categorieFournisseurId)
                .orElseThrow(() -> new EntityNotFoundException("Zone not found with id: " + categorieFournisseurId));

        existingCategorieFournisseur.softDelete(); // Utilisez la méthode de suppression logique définie dans l'entité
        categorieFournisseurRepository.save(existingCategorieFournisseur);
    }

    //LISTE
    public List<CategorieFournisseur> getAllCategorieFournisseur() {
        return categorieFournisseurRepository.findAll();
    }

    /// / //////////////////// TYPE FOURNISSEUR //////////////////////////////////////////////////////////
    //AJOUT
    public TypeFournisseur addTypeFournisseur(TypeFournisseur typeFournisseur) {
        try {
            typeFournisseurRepository.save(typeFournisseur);
            return typeFournisseur;
        } catch (Exception e) {
            throw e;
        }
    }

    //MODIFICATION
    public TypeFournisseur updateTypeFournisseur(TypeFournisseur updatedTypeFournisseur) {
        try {
            // Vérifier si la zone que vous souhaitez mettre à jour existe dans la base de données
            TypeFournisseur existingTypeFournisseur = typeFournisseurRepository.findById(updatedTypeFournisseur.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Zone not found with id: " + updatedTypeFournisseur.getId()));

            // Mettre à jour les propriétés de la zone existante avec les nouvelles valeurs
            existingTypeFournisseur.setDesignation(updatedTypeFournisseur.getDesignation());
            existingTypeFournisseur.setDescription(updatedTypeFournisseur.getDescription());

            // Enregistrer la mise à jour dans la base de données
            typeFournisseurRepository.save(existingTypeFournisseur);

            // Retourner la zone mise à jour
            return existingTypeFournisseur;
        } catch (Exception e) {
            // Gérer les exceptions, vous pouvez choisir de les logger ou de les relancer
            throw e;
        }
    }

    //Suppression
    public void softDeleteTypeFournisseur(Long typeFournisseurId) {
        TypeFournisseur existingTypeFournisseur = typeFournisseurRepository.findById(typeFournisseurId)
                .orElseThrow(() -> new EntityNotFoundException("Zone not found with id: " + typeFournisseurId));

        existingTypeFournisseur.softDelete(); // Utilisez la méthode de suppression logique définie dans l'entité
        typeFournisseurRepository.save(existingTypeFournisseur);
    }

    //LISTE
    public List<TypeFournisseur> getAllTypeFournisseur() {
        return typeFournisseurRepository.findAll();
    }

    //////////////////////////////////////////// CONTACT FOURNISSEUR ///////////////////////////////////////////

    //CREATION
    public ContactFournisseur createContactFournisseur(ContactFournisseur contactFournisseur) {
        try {
            contactFournistRepository.save(contactFournisseur);
            return contactFournisseur;
        }catch (Exception e){
            throw e;
        }
    }

    //MODIFICATION
    public ContactFournisseur updateContactFournisseur(ContactFournisseur updatedContactFournisseur) {
        try {
            // Vérifier si la zone que vous souhaitez mettre à jour existe dans la base de données
            ContactFournisseur existingContactFournisseur = contactFournistRepository.findById(updatedContactFournisseur.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Contact not found with id: " + updatedContactFournisseur.getId()));
            // Mettre à jour les propriétés de la zone existante avec les nouvelles valeurs
            existingContactFournisseur.setNom(updatedContactFournisseur.getNom());
            existingContactFournisseur.setTelephone(updatedContactFournisseur.getTelephone());
            existingContactFournisseur.setEmail(updatedContactFournisseur.getEmail());

            // Enregistrer la mise à jour dans la base de données
            contactFournistRepository.save(existingContactFournisseur);

            // Retourner la zone mise à jour
            return existingContactFournisseur;
        } catch (Exception e) {
            // Gérer les exceptions, vous pouvez choisir de les logger ou de les relancer
            throw e;
        }
    }
    // Suppression logique
    public void softDeleteContactFournisseur(Long contactFournisseurId) {
        ContactFournisseur existingContactFournisseur = contactFournistRepository.findById(contactFournisseurId)
                .orElseThrow(() -> new EntityNotFoundException("Contact not found with id: " + contactFournisseurId));

        existingContactFournisseur.softDelete(); // Utilisez la méthode de suppression logique définie dans l'entité
        contactFournistRepository.save(existingContactFournisseur);
    }

    //LISTE
    public List<ContactFournisseur> getAllContactFournisseur() {
        return contactFournistRepository.findAll();
    }
/////////////////////////////////// FOURNISSEUR //////////////////////////////////////////////
/////// AJOUT
    public Fournisseur addFournisseur(Fournisseur fournisseur) {
        try {
            fournisseurRepository.save(fournisseur);
            return fournisseur;
        } catch (Exception e) {
            throw e;
        }
    }
    //MODIFICATION
    @Transactional
    public Fournisseur updateFournisseur(Fournisseur fournisseur) {
        try {
            // Vérifier si la famille que vous souhaitez mettre à jour existe dans la base de données
            Fournisseur existingFournisseur = fournisseurRepository.findById(fournisseur.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Famille not found with id: " + fournisseur.getId()));

            // Mettre à jour les propriétés de la famille existante avec les nouvelles valeurs
            existingFournisseur.setNom(fournisseur.getNom());
            existingFournisseur.setAdresse(fournisseur.getAdresse());
            existingFournisseur.setTelephone(fournisseur.getTelephone());
            existingFournisseur.setNom(fournisseur.getAdresse());
            existingFournisseur.setEmail(fournisseur.getEmail());
            existingFournisseur.setTotalVersement(fournisseur.getTotalVersement());
            existingFournisseur.setSolde(fournisseur.getSolde());

            // Enregistrer la mise à jour dans la base de données
            fournisseurRepository.save(existingFournisseur);

            // Retourner la famille mise à jour
            return existingFournisseur;
        } catch (Exception e) {
            // Gérer les exceptions, vous pouvez choisir de les logger ou de les relancer
            throw e;
        }
    }

    //Suppression
    public Fournisseur softDeleteFournisseur(Long familleId) {
        Fournisseur existingFournisseur = fournisseurRepository.findById(familleId)
                .orElseThrow(() -> new EntityNotFoundException("Fournisseur not found with id: " + familleId));

        existingFournisseur.softDelete(); // Utilisez la méthode de suppression logique définie dans l'entité
        fournisseurRepository.save(existingFournisseur);
        return existingFournisseur == null ? null : existingFournisseur;
    }

    //LISTE
    public List<Fournisseur> getAllFournisseur() {
        return fournisseurRepository.findAll();
    }

    //LISTE + TYPE
    @Transactional()
    public List<Fournisseur> getAllFournisseurWithTypes() {
        List<Fournisseur> fournisseurs = fournisseurRepository.findAll();

        return fournisseurs.stream()
                .map(fournisseur -> new Fournisseur(
                        fournisseur.getId(),
                        fournisseur.getNom(),
                        fournisseur.getAdresse(),
                        fournisseur.getTelephone(),
                        fournisseur.getEmail(),
                        fournisseur.getTotalVersement(),
                        fournisseur.getSolde(),
                        fournisseur.getStatus(),
                        fournisseur.getCategorieFournisseur(),
                        fournisseur.getTypeFournisseur(),
                        fournisseur.getContactFournisseur()))
                .collect(Collectors.toList());
    }
}

