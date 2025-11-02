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