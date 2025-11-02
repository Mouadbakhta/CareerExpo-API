/**
 * SERVICE : Charge un admin pour Spring Security.
 * 
 * MÉTHODE PRINCIPALE :
 * - loadUserByUsername(String username) → retourne UserDetails
 *   → Appelle AdminRepository.findByUsername()
 *   → Lance UsernameNotFoundException si inexistant
 * 
 * ANNOTATION : @Service + implements UserDetailsService
 */