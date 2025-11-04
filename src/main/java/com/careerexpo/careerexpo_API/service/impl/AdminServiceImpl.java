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

package com.careerexpo.careerexpo_API.service.impl;

import com.careerexpo.careerexpo_API.entity.Admin;
import com.careerexpo.careerexpo_API.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl  {

    @Autowired
    private AdminRepository adminRepository;

}
