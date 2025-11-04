package com.careerexpo.careerexpo_API.repository;

import com.careerexpo.careerexpo_API.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    Competition findByAdminEmailAndPassword(String Email, String password);
    Competition findByAnne(LocalDate annee);
    boolean validateCompetition(Competition competition);
}
