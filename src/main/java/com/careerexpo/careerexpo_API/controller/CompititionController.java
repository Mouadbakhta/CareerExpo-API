/**
 * ROLE : Gérer les ateliers, challenges ou compétitions de CareerExpo.
 * 
 * ENDPOINTS :
 * 
 * - GET /api/competitions
 *   → Public → retourne la liste des compétitions (titre, date, description)
 *   → Utilisé sur la page d'accueil pour afficher le programme
 * 
 * - POST /api/competitions
 *   → Admin only → crée une nouvelle compétition
 * 
 * - PUT /api/competitions/{id}
 *   → Admin only → met à jour titre, date, récompense
 * 
 * - DELETE /api/competitions/{id}
 *   → Admin only → supprime
 * 
 * USAGE : Affiché dans le timing de l'événement sur le frontend.
 * 
 * DÉPENDANCES : CompetitionService
 */