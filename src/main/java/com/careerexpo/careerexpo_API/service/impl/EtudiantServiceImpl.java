/**
 * SERVICE : Logique métier des étudiants.
 * 
 * MÉTHODES :
 * - createEtudiant(EtudiantDTO, MultipartFile cv) → valide + sauvegarde + upload
 * - getAllEtudiants() → liste paginée
 * - getCvPath(Long id)
 * - deleteEtudiant(Long id) → supprime DB + fichier
 * - exportToCSV() → génère un fichier CSV des inscrits
 * 
 * DÉPENDANCES : EtudiantRepository, FileStorageService
 */

package com.careerexpo.careerexpo_API.service.impl;

import com.careerexpo.careerexpo_API.entity.Etudiant;
import com.careerexpo.careerexpo_API.repository.EtudiantRepository;
import com.careerexpo.careerexpo_API.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Optional;

@Service
public class EtudiantServiceImpl {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public Etudiant createEtudiant(Etudiant etudiant, MultipartFile cvFile) {
        if (cvFile == null || cvFile.isEmpty()) {
            throw new RuntimeException("CV file is required!");
        }


        String filename = fileStorageService.store(cvFile, etudiant.getNom(), etudiant.getPrenom());
        etudiant.setCvPath(filename);

        return etudiantRepository.save(etudiant);
    }

    public Page<Etudiant> getAllEtudiants(int page, int size) {
        return etudiantRepository.findAll(PageRequest.of(page, size));
    }

    public String getCvPath(Long id) {
        Etudiant e = etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etudiant not found"));
        return e.getCvPath();
    }

    public void deleteEtudiant(Long id) {
        Optional<Etudiant> optional = etudiantRepository.findById(id);
        if (optional.isPresent()) {
            Etudiant e = optional.get();
            fileStorageService.delete(e.getCvPath());
            etudiantRepository.deleteById(id);
        } else {
            throw new RuntimeException("Etudiant not found");
        }
    }

    public void exportToCSV() {
        try (PrintWriter writer = new PrintWriter(new File("etudiants.csv"))) {
            writer.println("ID,Nom,Prenom,Niveau,Filiere,CV");
            for (Etudiant e : etudiantRepository.findAll()) {
                writer.println(e.getId() + "," + e.getNom() + "," + e.getPrenom() + "," +
                        e.getNiveau() + "," + e.getFiliere() + "," + e.getCvPath());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error writing CSV: " + e.getMessage());
        }
    }
}
