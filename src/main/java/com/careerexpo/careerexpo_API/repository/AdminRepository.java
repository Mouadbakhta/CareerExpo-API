/**
 * REPOSITORY JPA : Accès aux données des admins.
 * 
 * MÉTHODES :
 * - extends JpaRepository<Admin, Long>
 * - findByemaile(String email) : Optional<Admin>
 */
package com.careerexpo.careerexpo_API.repository;


import com.careerexpo.careerexpo_API.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;



@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByemail(String email);
    Admin findByPassword(String password);
    Admin findByEmailAndPassword(String email, String password);


}
