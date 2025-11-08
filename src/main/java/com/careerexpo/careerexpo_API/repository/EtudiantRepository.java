package com.careerexpo.careerexpo_API.repository;

import com.careerexpo.careerexpo_API.entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

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

    @Query("SELECT DISTINCT e FROM Etudiant e LEFT JOIN FETCH e.competition c LEFT JOIN FETCH c.admin WHERE e.id = :id")
    Optional<Etudiant> findByIdWithCompetition(@Param("id") Long id);

    @Query("SELECT e FROM Etudiant e LEFT JOIN FETCH e.competition c LEFT JOIN FETCH c.admin WHERE c.id = :competitionId")
    List<Etudiant> findByCompetitionIdWithCompetition(@Param("competitionId") Long competitionId);

    @Modifying
    @Transactional
    @Query("UPDATE Etudiant e SET e.status = :status WHERE e.id = :id")
    int updateStatusById(@Param("id") Long id, @Param("status") Etudiant.Status status);
}