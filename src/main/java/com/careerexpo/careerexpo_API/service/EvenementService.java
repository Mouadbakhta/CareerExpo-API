/**
 * SERVICE : Logique des événements.
 * 
 * MÉTHODES :
 * - findAllOrdered() → tri par date
 * - findLatest() → événement actuel/futur
 * - save(), update()
 * 
 * USAGE : Page d'accueil + admin
 */

package com.careerexpo.careerexpo_API.service;

import com.careerexpo.careerexpo_API.entity.Evenement;
import com.careerexpo.careerexpo_API.repository.EvenementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvenementService {

    @Autowired
    private EvenementRepository evenementRepository;

    public List<Evenement> findAllOrdered() {
        return evenementRepository.findAllByOrderByDateDebutAsc();
    }

    public Optional<Evenement> findLatest() {
        List<Evenement> upcoming = evenementRepository.findUpcomingEvents();
        return upcoming.isEmpty() ? Optional.empty() : Optional.of(upcoming.get(0));
    }

    public Evenement save(Evenement evenement) {
        validateEvenement(evenement);
        return evenementRepository.save(evenement);
    }

    public Evenement update(Long id, Evenement evenement) {
        validateEvenement(evenement);
        Evenement existing = evenementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Événement non trouvé avec ID: " + id));

        existing.setNom(evenement.getNom());
        existing.setDescription(evenement.getDescription());
        existing.setDateDebut(evenement.getDateDebut());
        existing.setDateFin(evenement.getDateFin());

        return evenementRepository.save(existing);
    }

    private void validateEvenement(Evenement evenement) {
        if (evenement.getNom() == null || evenement.getNom().isEmpty()) {
            throw new IllegalArgumentException("Le nom de l'événement est requis");
        }
        if (evenement.getDateDebut() != null && evenement.getDateFin() != null &&
            evenement.getDateDebut().isAfter(evenement.getDateFin())) {
            throw new IllegalArgumentException("La date de début doit être avant la date de fin");
        }
    }
}
