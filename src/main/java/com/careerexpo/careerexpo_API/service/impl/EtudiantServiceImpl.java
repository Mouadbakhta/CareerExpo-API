package com.careerexpo.careerexpo_API.service.impl;

import com.careerexpo.careerexpo_API.entity.Etudiant;
import com.careerexpo.careerexpo_API.repository.EtudiantRepository;
import com.careerexpo.careerexpo_API.service.FileStorageService;
import com.careerexpo.careerexpo_API.service.facade.EtudiantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class EtudiantServiceImpl implements EtudiantService {

    private final EtudiantRepository etudiantRepository;
    private final FileStorageService fileStorageService; // Service pour gérer les fichiers

    public EtudiantServiceImpl(EtudiantRepository etudiantRepository, FileStorageService fileStorageService) {
        this.etudiantRepository = etudiantRepository;
        this.fileStorageService = fileStorageService;
    }

    @Override
    @Transactional
    public Etudiant createEtudiant(Etudiant etudiant) {
        if (!validateEtudiant(etudiant)) {
            throw new IllegalArgumentException("Données étudiant invalides");
        }

        if (existsByNomAndPrenom(etudiant.getNom(), etudiant.getPrenom())) {
            throw new IllegalArgumentException("Un étudiant avec ce nom et prénom existe déjà");
        }

        return etudiantRepository.save(etudiant);
    }

    @Override
    @Transactional
    public Etudiant createEtudiantWithCV(Etudiant etudiant, MultipartFile cvFile) {
        if (!validateEtudiant(etudiant)) {
            throw new IllegalArgumentException("Données étudiant invalides");
        }

        if (cvFile == null || cvFile.isEmpty()) {
            throw new IllegalArgumentException("Le fichier CV est requis");
        }

        if (existsByNomAndPrenom(etudiant.getNom(), etudiant.getPrenom())) {
            throw new IllegalArgumentException("Un étudiant avec ce nom et prénom existe déjà");
        }

        // Sauvegarder le fichier CV et obtenir le chemin
        String cvPath = fileStorageService.storeFile(cvFile);
        etudiant.setCvPath(cvPath);

        return etudiantRepository.save(etudiant);
    }

    @Override
    public Optional<Etudiant> getEtudiantById(Long id) {
        return etudiantRepository.findById(id);
    }

    @Override
    public Optional<Etudiant> getEtudiantWithCompetition(Long id) {
        return etudiantRepository.findByIdWithCompetition(id);
    }

    @Override
    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public List<Etudiant> getEtudiantsByCompetitionId(Long competitionId) {
        return etudiantRepository.findByCompetitionId(competitionId);
    }

    @Override
    public List<Etudiant> getEtudiantsByCompetitionIdWithCompetition(Long competitionId) {
        return etudiantRepository.findByCompetitionIdWithCompetition(competitionId);
    }

    @Override
    public List<Etudiant> getEtudiantsByEtablissement(String etablissement) {
        return etudiantRepository.findByEtablissement(etablissement);
    }

    @Override
    public List<Etudiant> getEtudiantsByEtablissementAndCompetition(String etablissement, Long competitionId) {
        return etudiantRepository.findByEtablissementAndCompetitionId(etablissement, competitionId);
    }

    @Override
    public Optional<Etudiant> getEtudiantByNomAndPrenom(String nom, String prenom) {
        return etudiantRepository.findByNomAndPrenom(nom, prenom);
    }

    @Override
    @Transactional
    public Etudiant updateEtudiant(Long id, Etudiant etudiant) {
        Etudiant existingEtudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé avec l'id: " + id));

        existingEtudiant.setNom(etudiant.getNom());
        existingEtudiant.setPrenom(etudiant.getPrenom());
        existingEtudiant.setEtablissement(etudiant.getEtablissement());

        if (etudiant.getCvPath() != null) {
            existingEtudiant.setCvPath(etudiant.getCvPath());
        }

        if (etudiant.getCompetition() != null) {
            existingEtudiant.setCompetition(etudiant.getCompetition());
        }

        if (!validateEtudiant(existingEtudiant)) {
            throw new IllegalArgumentException("Données étudiant invalides");
        }

        return etudiantRepository.save(existingEtudiant);
    }

    @Override
    @Transactional
    public Etudiant updateEtudiantCV(Long id, MultipartFile cvFile) {
        Etudiant existingEtudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé avec l'id: " + id));

        if (cvFile == null || cvFile.isEmpty()) {
            throw new IllegalArgumentException("Le fichier CV est requis");
        }

        // Supprimer l'ancien CV si nécessaire
        if (existingEtudiant.getCvPath() != null) {
            fileStorageService.deleteFile(existingEtudiant.getCvPath());
        }

        // Sauvegarder le nouveau CV
        String cvPath = fileStorageService.storeFile(cvFile);
        existingEtudiant.setCvPath(cvPath);

        return etudiantRepository.save(existingEtudiant);
    }

    @Override
    @Transactional
    public void deleteEtudiant(Long id) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé avec l'id: " + id));

        // Supprimer le CV associé
        if (etudiant.getCvPath() != null) {
            fileStorageService.deleteFile(etudiant.getCvPath());
        }

        etudiantRepository.deleteById(id);
    }

    @Override
    public long countEtudiantsByCompetition(Long competitionId) {
        return etudiantRepository.countByCompetitionId(competitionId);
    }

    @Override
    public boolean existsByNomAndPrenom(String nom, String prenom) {
        return etudiantRepository.existsByNomAndPrenom(nom, prenom);
    }

    @Override
    @Transactional
    public Etudiant validerEtudiant(Long id) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé avec l'id: " + id));

        // Logique de validation (peut être étendue selon vos besoins)
        // Par exemple: ajouter un champ 'statut' à l'entité Etudiant
        // etudiant.setStatut("VALIDÉ");

        return etudiantRepository.save(etudiant);
    }

    @Override
    @Transactional
    public void refuserEtudiant(Long id) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé avec l'id: " + id));

        // Logique de refus
        // Option 1: Supprimer l'étudiant
        deleteEtudiant(id);

        // Option 2: Marquer comme refusé (nécessite un champ 'statut')
        // etudiant.setStatut("REFUSÉ");
        // etudiantRepository.save(etudiant);
    }

    @Override
    public boolean validateEtudiant(Etudiant etudiant) {
        if (etudiant == null) {
            return false;
        }

        if (etudiant.getNom() == null || etudiant.getNom().isBlank()) {
            return false;
        }

        if (etudiant.getPrenom() == null || etudiant.getPrenom().isBlank()) {
            return false;
        }

        if (etudiant.getEtablissement() == null || etudiant.getEtablissement().isBlank()) {
            return false;
        }

        if (etudiant.getCompetition() == null) {
            return false;
        }

        // cvPath peut être null lors de la création si uploadé séparément
        // if (etudiant.getCvPath() == null || etudiant.getCvPath().isBlank()) {
        //     return false;
        // }

        return true;
    }
}