package com.careerexpo.careerexpo_API.service.impl;

import com.careerexpo.careerexpo_API.entity.Etudiant;
import com.careerexpo.careerexpo_API.entity.Competition;
import com.careerexpo.careerexpo_API.repository.EtudiantRepository;
import com.careerexpo.careerexpo_API.repository.CompetitionRepository;
import com.careerexpo.careerexpo_API.service.FileStorageService;
import com.careerexpo.careerexpo_API.service.facade.EtudiantService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class EtudiantServiceImpl implements EtudiantService {

    private final EtudiantRepository etudiantRepository;
    private final FileStorageService fileStorageService; // Service pour gérer les fichiers
    private final CompetitionRepository competitionRepository;

    public EtudiantServiceImpl(EtudiantRepository etudiantRepository, FileStorageService fileStorageService, CompetitionRepository competitionRepository) {
        this.etudiantRepository = etudiantRepository;
        this.fileStorageService = fileStorageService;
        this.competitionRepository = competitionRepository;
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

        // Assure un statut par défaut si absent
        if (etudiant.getStatus() == null) {
            etudiant.setStatus(Etudiant.Status.PENDING);
        }

        // Attacher une compétition gérée si seule l'ID est fournie
        if (etudiant.getCompetition() != null && etudiant.getCompetition().getId() != null) {
            Competition managedCompetition = competitionRepository.findById(etudiant.getCompetition().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Compétition introuvable avec l'id: " + etudiant.getCompetition().getId()));
            etudiant.setCompetition(managedCompetition);
        }

        return etudiantRepository.save(etudiant);
    }

    @Override
    @Transactional
    public Etudiant createEtudiantWithCV(Etudiant etudiant, MultipartFile cvPdfFile, MultipartFile cvVideoFile) {
        if (!validateEtudiant(etudiant)) {
            throw new IllegalArgumentException("Données étudiant invalides");
        }

        if (cvPdfFile == null || cvPdfFile.isEmpty()) {
            throw new IllegalArgumentException("Le fichier CV PDF est requis");
        }

        if (existsByNomAndPrenom(etudiant.getNom(), etudiant.getPrenom())) {
            throw new IllegalArgumentException("Un étudiant avec ce nom et prénom existe déjà");
        }

        // Créer un dossier pour l'étudiant
        Path studentDir = fileStorageService.createStudentDirectory(etudiant.getNom(), etudiant.getPrenom());

        // Format the file names
        String pdfFileName = "CV"+ etudiant.getPrenom()+"_"+etudiant.getNom()+".pdf";
        String videoFileName = "Video"+ etudiant.getPrenom()+"_"+etudiant.getNom()+".mp4";;

        // Sauvegarder le fichier CV PDF dans le dossier de l'étudiant
        String cvPdfPath = fileStorageService.storeFile(cvPdfFile, pdfFileName, studentDir);
        etudiant.setCvPdfPath(cvPdfPath);

        // Sauvegarder le fichier vidéo si fourni
        if (cvVideoFile != null && !cvVideoFile.isEmpty()) {
            String cvVideoPath = fileStorageService.storeFile(cvVideoFile, videoFileName, studentDir);
            etudiant.setCvVideoPath(cvVideoPath);
        }

        // Assure un statut par défaut si absent
        if (etudiant.getStatus() == null) {
            etudiant.setStatus(Etudiant.Status.PENDING);
        }

        // Attacher une compétition gérée si seule l'ID est fournie
        if (etudiant.getCompetition() != null && etudiant.getCompetition().getId() != null) {
            Competition managedCompetition = competitionRepository.findById(etudiant.getCompetition().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Compétition introuvable avec l'id: " + etudiant.getCompetition().getId()));
            etudiant.setCompetition(managedCompetition);
        }

        return etudiantRepository.save(etudiant);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Etudiant> getEtudiantById(Long id) {
        return etudiantRepository.findByIdWithCompetition(id);
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
                .orElseThrow(() -> new IllegalArgumentException("Étudiant non trouvé avec l'id: " + id));

        existingEtudiant.setNom(etudiant.getNom());
        existingEtudiant.setPrenom(etudiant.getPrenom());
        existingEtudiant.setEtablissement(etudiant.getEtablissement());

        if (etudiant.getNiveau() != null) {
            existingEtudiant.setNiveau(etudiant.getNiveau());
        }

        if (etudiant.getStatus() != null) {
            existingEtudiant.setStatus(etudiant.getStatus());
        }

        if (etudiant.getCompetition() != null) {
            if (etudiant.getCompetition().getId() == null) {
                throw new IllegalArgumentException("L'identifiant de la compétition est requis");
            }
            Competition managedCompetition = competitionRepository.findById(etudiant.getCompetition().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Compétition introuvable avec l'id: " + etudiant.getCompetition().getId()));
            existingEtudiant.setCompetition(managedCompetition);
        }

        if (!validateEtudiant(existingEtudiant)) {
            throw new IllegalArgumentException("Données étudiant invalides");
        }

        return etudiantRepository.save(existingEtudiant);
    }

    @Override
    @Transactional
    public Etudiant updateEtudiantCV(Long id, MultipartFile cvPdfFile, MultipartFile cvVideoFile) {
        Etudiant existingEtudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé avec l'id: " + id));

        if (cvPdfFile == null || cvPdfFile.isEmpty()) {
            throw new IllegalArgumentException("Le fichier CV PDF est requis");
        }

        // Obtenir ou créer le dossier de l'étudiant
        Path studentDir = fileStorageService.createStudentDirectory(existingEtudiant.getNom(), existingEtudiant.getPrenom());

        // Format the file names
        String pdfFileName = "CV"+ existingEtudiant.getPrenom()+"_"+existingEtudiant.getNom()+".pdf";
        String videoFileName = "Video"+ existingEtudiant.getPrenom()+"_"+existingEtudiant.getNom()+".mp4";

        // Supprimer l'ancien CV PDF si nécessaire
        if (existingEtudiant.getCvPdfPath() != null) {
            fileStorageService.deleteFile(existingEtudiant.getCvPdfPath());
        }

        // Sauvegarder le nouveau CV PDF
        String cvPdfPath = fileStorageService.storeFile(cvPdfFile, pdfFileName, studentDir);
        existingEtudiant.setCvPdfPath(cvPdfPath);

        // Gérer la vidéo CV si fournie
        if (cvVideoFile != null && !cvVideoFile.isEmpty()) {
            // Supprimer l'ancienne vidéo si elle existe
            if (existingEtudiant.getCvVideoPath() != null) {
                fileStorageService.deleteFile(existingEtudiant.getCvVideoPath());
            }
            String cvVideoPath = fileStorageService.storeFile(cvVideoFile, videoFileName, studentDir);
            existingEtudiant.setCvVideoPath(cvVideoPath);
        }

        return etudiantRepository.save(existingEtudiant);
    }

    @Override
    @Transactional
    public void deleteEtudiant(Long id) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé avec l'id: " + id));

        // Supprimer les fichiers CV associés
        if (etudiant.getCvPdfPath() != null) {
            fileStorageService.deleteFile(etudiant.getCvPdfPath());
        }
        if (etudiant.getCvVideoPath() != null) {
            fileStorageService.deleteFile(etudiant.getCvVideoPath());
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
        // Use a JPQL update to avoid triggering entity-level bean validation
        if (!etudiantRepository.existsById(id)) {
            throw new IllegalArgumentException("Étudiant non trouvé avec l'id: " + id);
        }

        int updated = etudiantRepository.updateStatusById(id, Etudiant.Status.ACCEPTED);
        if (updated == 0) {
            throw new RuntimeException("Impossible de valider l'étudiant (aucune ligne mise à jour)");
        }

        // Return the updated entity with competition eagerly fetched
        return etudiantRepository.findByIdWithCompetition(id)
                .orElseThrow(() -> new RuntimeException("Étudiant introuvable après mise à jour: " + id));
    }

    @Override
    @Transactional
    public void refuserEtudiant(Long id) {
        // Use JPQL update to avoid entity validation on fields like telephone or cvPdfPath
        if (!etudiantRepository.existsById(id)) {
            throw new RuntimeException("Étudiant non trouvé avec l'id: " + id);
        }

        int updated = etudiantRepository.updateStatusById(id, Etudiant.Status.DECLINED);
        if (updated == 0) {
            throw new RuntimeException("Impossible de refuser l'étudiant (aucune ligne mise à jour)");
        }
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

        if (etudiant.getTelephone() == null || etudiant.getTelephone().isBlank()) {
            return false;
        }

        if (etudiant.getNiveau() == null || etudiant.getNiveau().isBlank()) {
            return false;
        }

        if (etudiant.getCompetition() == null) {
            return false;
        }

        // cvPdfPath and cvVideoPath are validated separately during file upload

        return true;
    }
}