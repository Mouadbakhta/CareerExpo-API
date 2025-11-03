/**
 * REPOSITORY JPA : Accès aux compétitions.
 * 
 * MÉTHODES : CRUD standards via JpaRepository
 */
package com.careerexpo.careerexpo_API.repository;

import com.careerexpo.careerexpo_API.entity.Competition;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    //CRUD
    // la creation d'une compétition
    Competition save(Competition competition);

    // la lecture d'une compétition
    Competition getReferenceById(long id);
    // list de competitions par admin
    Competition findByAdminId(long id);

    // la modification de la compétition
    Competition update(Competition competition);

    // la suppression de la compétition
    void delete(Competition competition);
    // la suppression de la compétition par id
    void deleteById(long id);


    // compétition par annee
    Competition findByAnnee(String annee);
    // compétition par id
    Competition findById(long id);
    // compétition par description
    Competition findByDescription(String description);

}
