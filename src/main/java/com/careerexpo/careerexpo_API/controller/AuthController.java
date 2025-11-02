/**
 * ROLE : Gérer l'authentification des administrateurs.
 * 
 * FONCTIONNALITÉS :
 * - POST /api/auth/login
 *   → Reçoit { "username": "admin", "password": "password" }
 *   → Vérifie les identifiants dans la base (AdminRepository + BCrypt)
 *   → Si OK → génère un JWT (valide 24h) contenant : username, role=ADMIN, expiration
 *   → Retourne { "token": "eyJhbGciOi...", "role": "ADMIN", "expiresIn": 86400 }
 *   → Si KO → 401 Unauthorized
 * 
 * USAGE : Le frontend stocke le token dans localStorage et l'envoie dans les headers :
 *         Authorization: Bearer <token>
 * 
 * DÉPENDANCES : AdminService, JwtUtils, BCryptPasswordEncoder
 */