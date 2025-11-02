/**
 * SERVICE : Gestion des fichiers CV (upload, téléchargement, suppression).
 * 
 * MÉTHODES :
 * - init() → @PostConstruct → crée le dossier uploads/cv
 * - store(MultipartFile file, String nom, String prenom) → nom unique + sauvegarde
 * - load(String filename) → retourne Resource
 * - delete(String filename) → suppression physique
 * 
 * SÉCURITÉ : Vérifie extension .pdf, taille ≤ 5 Mo
 */


package com.careerexpo.careerexpo_API.service;

import jakarta.annotation.PostConstruct;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;


@Service
public class FileStorageService {

    private final Path uploadDir = Paths.get("src/main/resources/static/uploads/cv");

    @PostConstruct
    public void init() {
        try {
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
                System.out.println("Folder created: " + uploadDir.toAbsolutePath());
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory!", e);
        }
    }

    public String store(MultipartFile file, String nom, String prenom) {
        if (file.isEmpty()) {
            throw new RuntimeException("File is empty!");
        }


        if (!file.getOriginalFilename().toLowerCase().endsWith(".pdf")) {
            throw new RuntimeException("Only PDF files are allowed!");
        }

        if (file.getSize() > 5 * 1024 * 1024) {
            throw new RuntimeException("File too large! Max 5MB.");
        }

        String filename = nom + "_" + prenom + ".pdf";
        Path destination = uploadDir.resolve(filename);

        try {
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
            return filename;
        } catch (IOException e) {
            throw new RuntimeException("Error while saving file: " + e.getMessage());
        }
    }

    public Resource load(String filename) {
        try {
            Path filePath = uploadDir.resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("File not found: " + filename, e);
        }
    }

    public void delete(String filename) {
        try {
            Path filePath = uploadDir.resolve(filename);
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Could not delete file: " + filename);
        }
    }
}
