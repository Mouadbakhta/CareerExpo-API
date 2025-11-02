/**
 * ROLE : Fournir les données des éditions de CareerExpo (historique + actuel).
 * 
 * ENDPOINTS :
 * 
 * - GET /api/evenements
 *   → Public → retourne TOUTES les éditions triées par date (desc)
 *   → Utilisé pour l'historique sur la page d'accueil
 * 
 * - GET /api/evenements/current
 *   → Public → retourne l'événement le plus récent (ou futur)
 *   → Utilisé pour le compte à rebours et le timing
 * 
 * - GET /api/evenements/{id}
 *   → Public → détails d'une édition (guide, programme, partenaires)
 * 
 * - POST /api/evenements
 *   → Admin only → crée une nouvelle édition
 * 
 * - PUT /api/evenements/{id}
 *   → Admin only → met à jour le guide, le programme, etc.
 * 
 * DÉPENDANCES : EvenementService
 */