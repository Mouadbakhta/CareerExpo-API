package com.careerexpo.careerexpo_API.repository;

import com.careerexpo.careerexpo_API.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    Optional<Competition> findByAnnee(LocalDate annee);

    List<Competition> findAllByAnnee(LocalDate annee);

    List<Competition> findByAdminId(Long adminId);

    List<Competition> findByAdminEmail(String email);

    List<Competition> findByAnneeBetween(LocalDate startDate, LocalDate endDate);

    boolean existsByAnnee(LocalDate annee);

    @Query("SELECT c FROM Competition c LEFT JOIN FETCH c.etudiants WHERE c.id = :id")
    Optional<Competition> findByIdWithEtudiants(@Param("id") Long id);

    @Query("SELECT c FROM Competition c LEFT JOIN FETCH c.admin WHERE c.admin.id = :adminId")
    List<Competition> findByAdminIdWithAdmin(@Param("adminId") Long adminId);

    @Query("SELECT c FROM Competition c LEFT JOIN FETCH c.admin WHERE c.id = :id")
    Optional<Competition> findByIdWithAdmin(@Param("id") Long id);

    @Query("SELECT c FROM Competition c LEFT JOIN FETCH c.admin WHERE c.annee BETWEEN :startDate AND :endDate")
    List<Competition> findByYear(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
