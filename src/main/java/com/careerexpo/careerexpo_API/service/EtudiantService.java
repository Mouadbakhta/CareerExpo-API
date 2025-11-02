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