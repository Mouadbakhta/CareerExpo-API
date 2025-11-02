/**
 * ENTITÉ JPA : Représente un administrateur du site.
 * 
 * CHAMPS :
 * - id (Long, auto-incrémenté)
 * - username (String, unique, ex: "admin")
 * - password (String, hashé avec BCrypt)
 * - role (String, fixe : "ADMIN")
 * 
 * IMPORTE : Implémente UserDetails pour Spring Security
 *           → getAuthorities(), isEnabled(), etc.
 * 
 * TABLE : admins
 * 
 * USAGE : Utilisé par AdminService.loadUserByUsername()
 */