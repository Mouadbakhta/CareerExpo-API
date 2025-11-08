package com.careerexpo.careerexpo_API.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "etudiants")
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Column(nullable = false)
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    @Column(nullable = false)
    private String prenom;

    @NotBlank(message = "L'établissement est obligatoire")
    @Column(nullable = false)
    private String etablissement;

    @Column(columnDefinition = "VARCHAR(20) DEFAULT 'PENDING'")
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotBlank(message = "Le numéro de téléphone est obligatoire")
    @Column(nullable = false)
    private String telephone;

    @Column(nullable = true)
    private String cvPdfPath;

    @Column(nullable = true)
    private String cvVideoPath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "etudiants"})
    private Competition competition;

    @Column(columnDefinition = "VARCHAR(255)")
    @NotBlank
    private String niveau;

    public enum Status {
        PENDING, ACCEPTED, DECLINED
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Le nom est obligatoire") String getNom() {
        return nom;
    }

    public void setNom(@NotBlank(message = "Le nom est obligatoire") String nom) {
        this.nom = nom;
    }

    public @NotBlank(message = "Le prénom est obligatoire") String getPrenom() {
        return prenom;
    }

    public void setPrenom(@NotBlank(message = "Le prénom est obligatoire") String prenom) {
        this.prenom = prenom;
    }

    public @NotBlank(message = "L'établissement est obligatoire") String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(@NotBlank(message = "L'établissement est obligatoire") String etablissement) {
        this.etablissement = etablissement;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public @NotBlank(message = "Le numéro de téléphone est obligatoire") String getTelephone() {
        return telephone;
    }

    public void setTelephone(@NotBlank(message = "Le numéro de téléphone est obligatoire") String telephone) {
        this.telephone = telephone;
    }

    public @NotBlank(message = "Le chemin du CV PDF est obligatoire") String getCvPdfPath() {
        return cvPdfPath;
    }

    public void setCvPdfPath(@NotBlank(message = "Le chemin du CV PDF est obligatoire") String cvPdfPath) {
        this.cvPdfPath = cvPdfPath;
    }

    public String getCvVideoPath() {
        return cvVideoPath;
    }

    public void setCvVideoPath(String cvVideoPath) {
        this.cvVideoPath = cvVideoPath;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public @NotBlank String getNiveau() {
        return niveau;
    }

    public void setNiveau(@NotBlank String niveau) {
        this.niveau = niveau;
    }
}