/**
 * ROLE : Gérer les inscriptions des étudiants et leurs CV.
 * 
 * ENDPOINTS :
 * 
 * 1. POST /api/etudiants
 *    → Public (pas de login requis)
 *    → Reçoit : nom, prenom, niveau, filiere, cv (MultipartFile PDF)
 *    → Valide : champs non vides, PDF ≤ 5 Mo
 *    → Appelle FileStorageService.store() → sauvegarde dans uploads/cv
 *    → Sauvegarde l'étudiant en base avec cvPath
 *    → Retourne : { "message": "Inscription réussie", "etudiantId": 1 }
 * 
 * 2. GET /api/etudiants
 *    → Admin only (JWT requis)
 *    → Retourne liste paginée des étudiants (nom, prenom, niveau, filiere, date)
 * 
 * 3. GET /api/etudiants/{id}/cv
 *    → Admin only
 *    → Télécharge le PDF du CV (Content-Type: application/pdf)
 * 
 * 4. DELETE /api/etudiants/{id}
 *    → Admin only
 *    → Supprime l'étudiant + le fichier PDF du disque
 * 
 * DÉPENDANCES : EtudiantService, FileStorageService
 */