/**
 * REPOSITORY JPA : Accès aux étudiants.
 * 
 * MÉTHODES :
 * - extends JpaRepository<Etudiant, Long>
 */
package com.careerexpo.careerexpo_API.repository;

import com.careerexpo.careerexpo_API.entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    Etudiant save(Etudiant etudiant);

    Etudiant findById(long id);

    Etudiant update(Etudiant etudiant);

    void delete(Etudiant etudiant);

    void deleteById(long id);

    List<Etudiant> findByCompetitionId(long id);

}
