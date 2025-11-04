/**
 * REPOSITORY JPA : Accès aux compétitions.
 * 
 * MÉTHODES : CRUD standards via JpaRepository
 */
package com.careerexpo.careerexpo_API.repository;

import com.careerexpo.careerexpo_API.entity.Competition;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {

    Competition save(Competition competition);

    Competition getReferenceById(long id);

    Competition findByAdminId(long id);

    Competition update(Competition competition);

    void delete(Competition competition);

    void deleteById(long id);

    Competition findByAnnee(String annee);

    Competition findById(long id);

    Competition findByDescription(String description);

}
