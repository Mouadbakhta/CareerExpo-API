package com.careerexpo.careerexpo_API.service.impl;

import com.careerexpo.careerexpo_API.entity.Competition;
import com.careerexpo.careerexpo_API.repository.CompetitionRepository;
import com.careerexpo.careerexpo_API.service.facade.CompetitionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;

    public CompetitionServiceImpl(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @Override
    @Transactional
    public Competition createCompetition(Competition competition) {
        if (!validateCompetition(competition)) {
            throw new IllegalArgumentException("Données de compétition invalides");
        }

        if (competition.getId() == null && existsByAnnee(competition.getAnnee())) {
            throw new IllegalArgumentException("Une compétition existe déjà pour cette année");
        }

        return competitionRepository.save(competition);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Competition> getCompetitionById(Long id) {
        return competitionRepository.findByIdWithAdmin(id);
    }

    @Override
    public Optional<Competition> getCompetitionWithEtudiants(Long id) {
        return competitionRepository.findByIdWithEtudiants(id);
    }

    @Override
    public Optional<Competition> getCompetitionByAnnee(LocalDate annee) {
        return competitionRepository.findByAnnee(annee);
    }

    @Override
    public List<Competition> getAllCompetitionsByAnnee(LocalDate annee) {
        return competitionRepository.findAllByAnnee(annee);
    }

    @Override
    public List<Competition> getAllCompetitions() {
        return competitionRepository.findAll();
    }

    @Override
    public List<Competition> getCompetitionsByAdminId(Long adminId) {
        return competitionRepository.findByAdminId(adminId);
    }

    @Override
    public List<Competition> getCompetitionsByAdminIdWithAdmin(Long adminId) {
        return competitionRepository.findByAdminIdWithAdmin(adminId);
    }

    @Override
    public List<Competition> getCompetitionsByAdminEmail(String email) {
        return competitionRepository.findByAdminEmail(email);
    }

    @Override
    public List<Competition> getCompetitionsBetweenDates(LocalDate startDate, LocalDate endDate) {
        return competitionRepository.findByAnneeBetween(startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Competition> getCompetitionsByYear(int year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);
        return competitionRepository.findByYear(startDate, endDate);
    }

    @Override
    @Transactional
    public Competition updateCompetition(Long id, Competition competition) {
        Competition existingCompetition = competitionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compétition non trouvée avec l'id: " + id));

        existingCompetition.setDescription(competition.getDescription());
        existingCompetition.setAnnee(competition.getAnnee());

        if (competition.getAdmin() != null) {
            existingCompetition.setAdmin(competition.getAdmin());
        }

        if (!validateCompetition(existingCompetition)) {
            throw new IllegalArgumentException("Données de compétition invalides");
        }

        return competitionRepository.save(existingCompetition);
    }

    @Override
    @Transactional
    public void deleteCompetition(Long id) {
        if (!competitionRepository.existsById(id)) {
            throw new RuntimeException("Compétition non trouvée avec l'id: " + id);
        }
        competitionRepository.deleteById(id);
    }

    @Override
    public boolean existsByAnnee(LocalDate annee) {
        return competitionRepository.existsByAnnee(annee);
    }

    @Override
    public boolean validateCompetition(Competition competition) {
        if (competition == null) {
            return false;
        }

        if (competition.getDescription() == null || competition.getDescription().isBlank()) {
            return false;
        }

        if (competition.getAnnee() == null) {
            return false;
        }

        if (competition.getAdmin() == null) {
            return false;
        }

        // Optionnel: Vérifier que l'année n'est pas dans le passé
        // if (competition.getAnnee().isBefore(LocalDate.now())) {
        //     return false;
        // }

        return true;
    }
}