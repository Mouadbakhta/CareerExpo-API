package com.careerexpo.careerexpo_API.controller;

import com.careerexpo.careerexpo_API.entity.Etudiant;
import com.careerexpo.careerexpo_API.service.facade.EtudiantService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/etudiants")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EtudiantController {

    private final EtudiantService etudiantService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    // ✅ Create simple etudiant (no files)
    @PostMapping
    public ResponseEntity<Etudiant> createEtudiant(@Valid @RequestBody Etudiant etudiant) {
        try {
            Etudiant created = etudiantService.createEtudiant(etudiant);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    // ✅ Create etudiant with CV files (PDF + optional video)
    @PostMapping(value = "/with-cv", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Etudiant> createEtudiantWithCV(
            @RequestPart("etudiant") String etudiantJson,
            @RequestPart("cvPdf") MultipartFile cvPdfFile,
            @RequestPart(value = "cvVideo", required = false) MultipartFile cvVideoFile) {
        try {
            Etudiant etudiant = objectMapper.readValue(etudiantJson, Etudiant.class);

            // Validate file sizes (max 5 MB)
            if (cvPdfFile.getSize() > 5 * 1024 * 1024)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le fichier PDF ne doit pas dépasser 5 MB");
            if (cvVideoFile != null && cvVideoFile.getSize() > 5 * 1024 * 1024)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le fichier vidéo ne doit pas dépasser 5 MB");

            Etudiant created = etudiantService.createEtudiantWithCV(etudiant, cvPdfFile, cvVideoFile);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);

        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Format JSON invalide pour l'étudiant");
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    // ✅ Get all etudiants
    @GetMapping
    public ResponseEntity<List<Etudiant>> getAllEtudiants() {
        return ResponseEntity.ok(etudiantService.getAllEtudiants());
    }

    // ✅ Get etudiant by ID
    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> getEtudiantById(@PathVariable Long id) {
        return etudiantService.getEtudiantById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Get etudiant with competition
    @GetMapping("/{id}/with-competition")
    public ResponseEntity<Etudiant> getEtudiantWithCompetition(@PathVariable Long id) {
        return etudiantService.getEtudiantWithCompetition(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Get by competition
    @GetMapping("/competition/{competitionId}")
    public ResponseEntity<List<Etudiant>> getEtudiantsByCompetition(@PathVariable Long competitionId) {
        return ResponseEntity.ok(etudiantService.getEtudiantsByCompetitionId(competitionId));
    }

    // ✅ Get by competition with details
    @GetMapping("/competition/{competitionId}/with-details")
    public ResponseEntity<List<Etudiant>> getEtudiantsByCompetitionWithDetails(@PathVariable Long competitionId) {
        return ResponseEntity.ok(etudiantService.getEtudiantsByCompetitionIdWithCompetition(competitionId));
    }

    // ✅ Get by etablissement
    @GetMapping("/etablissement/{etablissement}")
    public ResponseEntity<List<Etudiant>> getEtudiantsByEtablissement(@PathVariable String etablissement) {
        return ResponseEntity.ok(etudiantService.getEtudiantsByEtablissement(etablissement));
    }

    // ✅ Get by etablissement + competition
    @GetMapping("/etablissement/{etablissement}/competition/{competitionId}")
    public ResponseEntity<List<Etudiant>> getEtudiantsByEtablissementAndCompetition(
            @PathVariable String etablissement, @PathVariable Long competitionId) {
        return ResponseEntity.ok(etudiantService.getEtudiantsByEtablissementAndCompetition(etablissement, competitionId));
    }

    // ✅ Search by nom + prenom
    @GetMapping("/search")
    public ResponseEntity<Etudiant> searchEtudiant(
            @RequestParam String nom,
            @RequestParam String prenom) {
        return etudiantService.getEtudiantByNomAndPrenom(nom, prenom)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Update etudiant (data only)
    @PutMapping("/{id}")
    public ResponseEntity<Etudiant> updateEtudiant(@PathVariable Long id, @Valid @RequestBody Etudiant etudiant) {
        try {
            Etudiant updated = etudiantService.updateEtudiant(id, etudiant);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    // ✅ Update CV (PDF + optional video)
    @PutMapping(value = "/{id}/cv", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Etudiant> updateEtudiantCV(
            @PathVariable Long id,
            @RequestPart("cvPdf") MultipartFile cvPdfFile,
            @RequestPart(value = "cvVideo", required = false) MultipartFile cvVideoFile) {
        try {
            Etudiant updated = etudiantService.updateEtudiantCV(id, cvPdfFile, cvVideoFile);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    // ✅ Delete etudiant
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEtudiant(@PathVariable Long id) {
        try {
            etudiantService.deleteEtudiant(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    // ✅ Validate etudiant
    @PostMapping("/{id}/validate")
    public ResponseEntity<Etudiant> validateEtudiant(@PathVariable Long id) {
        try {
            Etudiant validated = etudiantService.validerEtudiant(id);
            return ResponseEntity.ok(validated);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    // ✅ Refuse etudiant
    @PostMapping("/{id}/refuse")
    public ResponseEntity<Void> refuseEtudiant(@PathVariable Long id) {
        try {
            etudiantService.refuserEtudiant(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    // ✅ Count etudiants per competition
    @GetMapping("/competition/{competitionId}/count")
    public ResponseEntity<Long> countEtudiantsByCompetition(@PathVariable Long competitionId) {
        return ResponseEntity.ok(etudiantService.countEtudiantsByCompetition(competitionId));
    }
}
