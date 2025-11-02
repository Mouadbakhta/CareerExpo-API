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

package com.careerexpo.careerexpo_API.service;

import com.careerexpo.careerexpo_API.entity.Admin;
import com.careerexpo.careerexpo_API.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Admin non trouvé : " + username));

        return User.builder()
                .username(admin.getUsername())
                .password(admin.getPassword()) 
                .roles("ADMIN")
                .build();
    }
}
