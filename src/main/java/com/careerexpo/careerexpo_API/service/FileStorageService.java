package com.careerexpo.careerexpo_API.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    public FileStorageService(@Value("${file.upload-dir:uploads/cv}") String uploadDir) {
        this.fileStorageLocation = Paths.get(uploadDir)
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Impossible de créer le répertoire de stockage des fichiers.", ex);
        }
    }

    /**
     * Sauvegarder un fichier et retourner son chemin
     */
    public String storeFile(MultipartFile file) {
        // Nettoyer le nom du fichier
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Vérifier si le fichier contient des caractères invalides
            if (originalFileName.contains("..")) {
                throw new RuntimeException("Le nom du fichier contient une séquence de chemin invalide: " + originalFileName);
            }

            // Générer un nom de fichier unique
            String fileExtension = "";
            int lastDotIndex = originalFileName.lastIndexOf('.');
            if (lastDotIndex > 0) {
                fileExtension = originalFileName.substring(lastDotIndex);
            }

            String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

            // Copier le fichier vers l'emplacement cible
            Path targetLocation = this.fileStorageLocation.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return uniqueFileName;
        } catch (IOException ex) {
            throw new RuntimeException("Impossible de sauvegarder le fichier " + originalFileName + ". Réessayez!", ex);
        }
    }

    /**
     * Charger un fichier en tant que Resource
     */
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("Fichier non trouvé: " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("Fichier non trouvé: " + fileName, ex);
        }
    }

    /**
     * Supprimer un fichier
     */
    public void deleteFile(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Files.deleteIfExists(filePath);
        } catch (IOException ex) {
            throw new RuntimeException("Impossible de supprimer le fichier: " + fileName, ex);
        }
    }

    /**
     * Obtenir le chemin complet d'un fichier
     */
    public Path getFilePath(String fileName) {
        return this.fileStorageLocation.resolve(fileName).normalize();
    }
}