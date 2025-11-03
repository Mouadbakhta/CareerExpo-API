/**
 * REPOSITORY JPA : Accès aux données des admins.
 * 
 * MÉTHODES :
 * - extends JpaRepository<Admin, Long>
 * - findByUsername(String email) : Optional<Admin>
 * 
 * USAGE : Utilisé dans AuthController et AdminService
 */
package com.careerexpo.careerexpo_API.repository;


import com.careerexpo.careerexpo_API.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByemail(String email);
    Admin findByPassword(String password);
    Admin findByEmailAndPassword(String email, String password);


}
