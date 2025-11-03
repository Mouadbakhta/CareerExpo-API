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
package com.careerexpo.careerexpo_API.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "admins")
public class Admin  implements  UserDetails        {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column (unique = true )
    private String email;
    @JsonIgnore
    private String password;


}

