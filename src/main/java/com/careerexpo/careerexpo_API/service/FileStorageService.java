package com.careerexpo.careerexpo_API.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import io.micrometer.common.lang.NonNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


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
    /**
     * Créer un dossier pour un étudiant
     */
    public Path createStudentDirectory(String nom, String prenom) {
        String studentDirName = StringUtils.cleanPath(nom + "_" + prenom);
        Path studentDir = this.fileStorageLocation.resolve(studentDirName);
        try {
            Files.createDirectories(studentDir);
            return studentDir;
        } catch (IOException ex) {
            throw new RuntimeException("Impossible de créer le dossier pour l'étudiant: " + studentDirName, ex);
        }
    }

    public String storeFile(MultipartFile file, String customFileName, Path directory) {
        try {
            String fileName = customFileName;
            if (fileName == null || fileName.isEmpty()) {
                // Use original filename if custom name is not provided
                
                String original = file.getOriginalFilename() == null ? "" : file.getOriginalFilename();
                fileName = StringUtils.cleanPath(original);
            }

            // Vérifier si le fichier contient des caractères invalides
            if (fileName.contains("..")) {
                throw new RuntimeException("Le nom du fichier contient une séquence de chemin invalide: " + fileName);
            }

            // S'assurer que le dossier existe
            Files.createDirectories(directory);

            // Add file extension if not present in custom filename
            if (!fileName.contains(".")) {
                String originalFileName = file.getOriginalFilename();
                String fileExtension = "";
                int lastDotIndex = originalFileName != null ? originalFileName.lastIndexOf('.') : -1;
                if (lastDotIndex > 0) {
                    fileExtension = originalFileName.substring(lastDotIndex);
                }
                fileName = fileName + fileExtension;
            }

            // Make filename unique by adding timestamp if file exists
            String finalFileName = fileName;
            Path targetLocation = this.fileStorageLocation.resolve(finalFileName);
            if (Files.exists(targetLocation)) {
                String nameWithoutExt = fileName;
                String extension = "";
                int lastDotIndex = fileName.lastIndexOf('.');
                if (lastDotIndex > 0) {
                    nameWithoutExt = fileName.substring(0, lastDotIndex);
                    extension = fileName.substring(lastDotIndex);
                }
                finalFileName = nameWithoutExt + "_" + System.currentTimeMillis() + extension;
            }

            // Utiliser le dossier spécifié ou le dossier par défaut
            targetLocation = directory != null ? 
                directory.resolve(finalFileName) : 
                this.fileStorageLocation.resolve(finalFileName);

            // Copier le fichier vers l'emplacement cible
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return directory.relativize(targetLocation).toString();
        } catch (IOException ex) {
            throw new RuntimeException("Impossible de sauvegarder le fichier. Réessayez!", ex);
        }
    }

    public String storeFile(MultipartFile file) {
        return storeFile(file, null, this.fileStorageLocation);
    }

    public String storeFile(MultipartFile file, String customFileName) {
        return storeFile(file, customFileName, this.fileStorageLocation);
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