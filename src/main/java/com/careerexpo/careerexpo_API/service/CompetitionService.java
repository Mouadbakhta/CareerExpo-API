/**
 * SERVICE : Logique des compétitions.
 * 
 * MÉTHODES CRUD :
 * - findAll(), findById(), save(), update(), delete()
 * 
 * VALIDATION : dates cohérentes, champs requis
 */

package com.careerexpo.careerexpo_API.service;

import com.careerexpo.careerexpo_API.entity.Competition;
import com.careerexpo.careerexpo_API.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompetitionService {

    @Autowired
    private CompetitionRepository competitionRepository;

    public List<Competition> findAll() {
        return competitionRepository.findAll();
    }

    public Optional<Competition> findById(Long id) {
        return competitionRepository.findById(id);
    }

    public Competition save(Competition competition) {
        validateCompetition(competition);
        return competitionRepository.save(competition);
    }

    public Competition update(Long id, Competition competition) {
        validateCompetition(competition);
        Competition existing = competitionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compétition non trouvée avec ID: " + id));

        existing.setNom(competition.getNom());
        existing.setDescription(competition.getDescription());
        existing.setDateDebut(competition.getDateDebut());
        existing.setDateFin(competition.getDateFin());

        return competitionRepository.save(existing);
    }

    public void delete(Long id) {
        competitionRepository.deleteById(id);
    }

    private void validateCompetition(Competition competition) {
        if (competition.getNom() == null || competition.getNom().isEmpty()) {
            throw new IllegalArgumentException("Le nom de la compétition est requis");
        }
        if (competition.getDateDebut() != null && competition.getDateFin() != null &&
            competition.getDateDebut().isAfter(competition.getDateFin())) {
            throw new IllegalArgumentException("La date de début doit être avant la date de fin");
        }
    }
}
