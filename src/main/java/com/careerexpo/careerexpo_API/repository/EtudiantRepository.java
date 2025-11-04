package com.careerexpo.careerexpo_API.repository;

import com.careerexpo.careerexpo_API.entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    List<Etudiant> findByCompetitionId(Long competitionId);

    Optional<Etudiant> findByNomAndPrenom(String nom, String prenom);

    List<Etudiant> findByEtablissement(String etablissement);

    List<Etudiant> findByEtablissementAndCompetitionId(String etablissement, Long competitionId);

    boolean existsByNomAndPrenom(String nom, String prenom);

    long countByCompetitionId(Long competitionId);

    @Query("SELECT e FROM Etudiant e LEFT JOIN FETCH e.competition WHERE e.id = :id")
    Optional<Etudiant> findByIdWithCompetition(@Param("id") Long id);

    @Query("SELECT e FROM Etudiant e LEFT JOIN FETCH e.competition c WHERE c.id = :competitionId")
    List<Etudiant> findByCompetitionIdWithCompetition(@Param("competitionId") Long competitionId);
}