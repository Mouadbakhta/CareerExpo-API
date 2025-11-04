package com.careerexpo.careerexpo_API.service.facade;

import com.careerexpo.careerexpo_API.entity.Etudiant;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface EtudiantService {

    Etudiant createEtudiant(Etudiant etudiant);
    Etudiant createEtudiantWithCV(Etudiant etudiant, MultipartFile cvFile);
    Optional<Etudiant> getEtudiantById(Long id);
    Optional<Etudiant> getEtudiantWithCompetition(Long id);
    List<Etudiant> getAllEtudiants();
    List<Etudiant> getEtudiantsByCompetitionId(Long competitionId);
    List<Etudiant> getEtudiantsByCompetitionIdWithCompetition(Long competitionId);
    List<Etudiant> getEtudiantsByEtablissement(String etablissement);
    List<Etudiant> getEtudiantsByEtablissementAndCompetition(String etablissement, Long competitionId);
    Optional<Etudiant> getEtudiantByNomAndPrenom(String nom, String prenom);
    Etudiant updateEtudiant(Long id, Etudiant etudiant);
    Etudiant updateEtudiantCV(Long id, MultipartFile cvFile);
    void deleteEtudiant(Long id);
    long countEtudiantsByCompetition(Long competitionId);
    boolean existsByNomAndPrenom(String nom, String prenom);
    Etudiant validerEtudiant(Long id);
    void refuserEtudiant(Long id);
    boolean validateEtudiant(Etudiant etudiant);
}
