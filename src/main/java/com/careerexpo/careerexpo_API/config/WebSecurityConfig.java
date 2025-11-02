/**
 * CONFIGURATION SÉCURITÉ : Spring Security + JWT.
 * 
 * FONCTIONNALITÉS :
 * - Désactive CSRF (API REST)
 * - CORS : autorise http://localhost:3000 (frontend)
 * - Endpoints publics :
 *   → POST /api/auth/login
 *   → POST /api/etudiants
 *   → GET /api/evenements/**
 *   → GET /api/competitions
 * - Tout le reste → nécessite JWT + rôle ADMIN
 * - JWT Filter : valide le token à chaque requête
 * - BCryptPasswordEncoder bean
 */