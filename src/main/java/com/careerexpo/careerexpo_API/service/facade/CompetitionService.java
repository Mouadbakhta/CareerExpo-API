package com.careerexpo.careerexpo_API.service.facade;

import com.careerexpo.careerexpo_API.entity.Competition;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CompetitionService {

    Competition createCompetition(Competition competition);
    Optional<Competition> getCompetitionById(Long id);
    Optional<Competition> getCompetitionWithEtudiants(Long id);
    Optional<Competition> getCompetitionByAnnee(LocalDate annee);
    List<Competition> getAllCompetitionsByAnnee(LocalDate annee);
    List<Competition> getAllCompetitions();
    List<Competition> getCompetitionsByAdminId(Long adminId);
    List<Competition> getCompetitionsByAdminIdWithAdmin(Long adminId);
    List<Competition> getCompetitionsByAdminEmail(String email);
    List<Competition> getCompetitionsBetweenDates(LocalDate startDate, LocalDate endDate);
    List<Competition> getCompetitionsByYear(int year);
    Competition updateCompetition(Long id, Competition competition);
    void deleteCompetition(Long id);
    boolean existsByAnnee(LocalDate annee);
    boolean validateCompetition(Competition competition);
}
