package com.careerexpo.careerexpo_API.controller;

import com.careerexpo.careerexpo_API.entity.Etudiant;
import com.careerexpo.careerexpo_API.service.facade.EtudiantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/etudiants")

@CrossOrigin(origins = "*", maxAge = 3600)
public class EtudiantController {

    private final EtudiantService etudiantService;

    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    // Create a new etudiant
    @PostMapping
    public ResponseEntity<Etudiant> createEtudiant(@Valid @RequestBody Etudiant etudiant) {
        try {
            Etudiant createdEtudiant = etudiantService.createEtudiant(etudiant);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEtudiant);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    // Create etudiant with CV file
    @PostMapping(value = "/with-cv", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Etudiant> createEtudiantWithCV(
            @RequestPart(value = "etudiant", required = false) String etudiantJson,
            @RequestParam(value = "etudiant", required = false) String etudiantJsonParam,
            @RequestPart("cv") MultipartFile cvFile) {
        try {
            String effectiveEtudiantJson = etudiantJson != null ? etudiantJson : etudiantJsonParam;
            if (effectiveEtudiantJson == null || effectiveEtudiantJson.isBlank()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Required part 'etudiant' is missing or empty");
            }
            Etudiant etudiant = new ObjectMapper().readValue(effectiveEtudiantJson, Etudiant.class);
            Etudiant createdEtudiant = etudiantService.createEtudiantWithCV(etudiant, cvFile);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEtudiant);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid etudiant JSON");
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    // Get etudiant by ID
    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> getEtudiantById(@PathVariable Long id) {
        return etudiantService.getEtudiantById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get etudiant by ID with competition details
    @GetMapping("/{id}/with-competition")
    public ResponseEntity<Etudiant> getEtudiantWithCompetition(@PathVariable Long id) {
        return etudiantService.getEtudiantWithCompetition(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get all etudiants
    @GetMapping
    public ResponseEntity<List<Etudiant>> getAllEtudiants() {
        List<Etudiant> etudiants = etudiantService.getAllEtudiants();
        return ResponseEntity.ok(etudiants);
    }

    // Get etudiants by competition ID
    @GetMapping("/competition/{competitionId}")
    public ResponseEntity<List<Etudiant>> getEtudiantsByCompetitionId(@PathVariable Long competitionId) {
        List<Etudiant> etudiants = etudiantService.getEtudiantsByCompetitionId(competitionId);
        return ResponseEntity.ok(etudiants);
    }

    // Get etudiants by competition ID with competition details
    @GetMapping("/competition/{competitionId}/with-details")
    public ResponseEntity<List<Etudiant>> getEtudiantsByCompetitionIdWithCompetition(@PathVariable Long competitionId) {
        List<Etudiant> etudiants = etudiantService.getEtudiantsByCompetitionIdWithCompetition(competitionId);
        return ResponseEntity.ok(etudiants);
    }

    // Get etudiants by etablissement (school/institute)
    @GetMapping("/etablissement/{etablissement}")
    public ResponseEntity<List<Etudiant>> getEtudiantsByEtablissement(@PathVariable String etablissement) {
        List<Etudiant> etudiants = etudiantService.getEtudiantsByEtablissement(etablissement);
        return ResponseEntity.ok(etudiants);
    }

    // Get etudiants by etablissement and competition
    @GetMapping("/etablissement/{etablissement}/competition/{competitionId}")
    public ResponseEntity<List<Etudiant>> getEtudiantsByEtablissementAndCompetition(
            @PathVariable String etablissement,
            @PathVariable Long competitionId) {
        List<Etudiant> etudiants = etudiantService.getEtudiantsByEtablissementAndCompetition(etablissement, competitionId);
        return ResponseEntity.ok(etudiants);
    }

    // Get etudiant by nom and prenom
    @GetMapping("/search")
    public ResponseEntity<Etudiant> getEtudiantByNomAndPrenom(
            @RequestParam String nom,
            @RequestParam String prenom) {
        return etudiantService.getEtudiantByNomAndPrenom(nom, prenom)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update etudiant
    @PutMapping("/{id}")
    public ResponseEntity<Etudiant> updateEtudiant(@PathVariable Long id, @Valid @RequestBody Etudiant etudiant) {
        try {
            Etudiant updatedEtudiant = etudiantService.updateEtudiant(id, etudiant);
            return ResponseEntity.ok(updatedEtudiant);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Update etudiant CV
    @PutMapping("/{id}/cv")
    public ResponseEntity<Etudiant> updateEtudiantCV(
            @PathVariable Long id,
            @RequestPart("cv") MultipartFile cvFile) {
        try {
            Etudiant updatedEtudiant = etudiantService.updateEtudiantCV(id, cvFile);
            return ResponseEntity.ok(updatedEtudiant);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Delete etudiant
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEtudiant(@PathVariable Long id) {
        try {
            etudiantService.deleteEtudiant(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Validate etudiant (accept)
    @PostMapping("/{id}/validate")
    public ResponseEntity<Etudiant> validerEtudiant(@PathVariable Long id) {
        try {
            Etudiant validatedEtudiant = etudiantService.validerEtudiant(id);
            return ResponseEntity.ok(validatedEtudiant);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Refuse etudiant (decline)
    @PostMapping("/{id}/refuse")
    public ResponseEntity<Void> refuserEtudiant(@PathVariable Long id) {
        try {
            etudiantService.refuserEtudiant(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Count etudiants by competition
    @GetMapping("/competition/{competitionId}/count")
    public ResponseEntity<Long> countEtudiantsByCompetition(@PathVariable Long competitionId) {
        long count = etudiantService.countEtudiantsByCompetition(competitionId);
        return ResponseEntity.ok(count);
    }
}
