package com.careerexpo.careerexpo_API.controller;

import com.careerexpo.careerexpo_API.entity.Competition;
import com.careerexpo.careerexpo_API.service.facade.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/competitions")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class CompetitionController {

    private final CompetitionService competitionService;

    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    // Create a new competition
    @PostMapping
    public ResponseEntity<Competition> createCompetition(@Valid @RequestBody Competition competition) {
        try {
            Competition createdCompetition = competitionService.createCompetition(competition);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCompetition);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Get competition by ID
    @GetMapping("/{id}")
    public ResponseEntity<Competition> getCompetitionById(@PathVariable Long id) {
        return competitionService.getCompetitionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get competition by ID with etudiants
    @GetMapping("/{id}/with-etudiants")
    public ResponseEntity<Competition> getCompetitionWithEtudiants(@PathVariable Long id) {
        return competitionService.getCompetitionWithEtudiants(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get competition by year
    @GetMapping("/annee/{annee}")
    public ResponseEntity<Competition> getCompetitionByAnnee(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate annee) {
        return competitionService.getCompetitionByAnnee(annee)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get all competitions by year
    @GetMapping("/year/{annee}")
    public ResponseEntity<List<Competition>> getAllCompetitionsByAnnee(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate annee) {
        List<Competition> competitions = competitionService.getAllCompetitionsByAnnee(annee);
        return ResponseEntity.ok(competitions);
    }

    // Get all competitions
    @GetMapping
    public ResponseEntity<List<Competition>> getAllCompetitions() {
        List<Competition> competitions = competitionService.getAllCompetitions();
        return ResponseEntity.ok(competitions);
    }

    // Get competitions by admin ID
    @GetMapping("/admin/{adminId}")
    public ResponseEntity<List<Competition>> getCompetitionsByAdminId(@PathVariable Long adminId) {
        List<Competition> competitions = competitionService.getCompetitionsByAdminId(adminId);
        return ResponseEntity.ok(competitions);
    }

    // Get competitions by admin ID with admin details
    @GetMapping("/admin/{adminId}/with-details")
    public ResponseEntity<List<Competition>> getCompetitionsByAdminIdWithAdmin(@PathVariable Long adminId) {
        List<Competition> competitions = competitionService.getCompetitionsByAdminIdWithAdmin(adminId);
        return ResponseEntity.ok(competitions);
    }

    // Get competitions by admin email
    @GetMapping("/admin-email/{email}")
    public ResponseEntity<List<Competition>> getCompetitionsByAdminEmail(@PathVariable String email) {
        List<Competition> competitions = competitionService.getCompetitionsByAdminEmail(email);
        return ResponseEntity.ok(competitions);
    }

    // Get competitions between dates
    @GetMapping("/between")
    public ResponseEntity<List<Competition>> getCompetitionsBetweenDates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Competition> competitions = competitionService.getCompetitionsBetweenDates(startDate, endDate);
        return ResponseEntity.ok(competitions);
    }

    // Update competition
    @PutMapping("/{id}")
    public ResponseEntity<Competition> updateCompetition(@PathVariable Long id, @Valid @RequestBody Competition competition) {
        try {
            Competition updatedCompetition = competitionService.updateCompetition(id, competition);
            return ResponseEntity.ok(updatedCompetition);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Delete competition
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompetition(@PathVariable Long id) {
        try {
            competitionService.deleteCompetition(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
