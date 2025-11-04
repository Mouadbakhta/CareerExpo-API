/**CREATE TABLE etudiant (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        nom VARCHAR(50) NOT NULL,
prenom VARCHAR(50) NOT NULL,
etablissement VARCHAR(100) NOT NULL,
cv_path VARCHAR(255),
competition_id BIGINT NOT NULL,
FOREIGN KEY (competition_id) REFERENCES competition(id)
        );

 **/

package com.careerexpo.careerexpo_API.entity;

import  jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "etudiants")
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Column(nullable = false)
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    @Column(nullable = false)
    private String prenom;

    @NotBlank(message = "L'établissement est obligatoire")
    @Column(nullable = false)
    private String etablissement;

    @NotBlank(message = "Le chemin du CV est obligatoire")
    @Column(nullable = false)
    private String cvPath;


    @ManyToOne
    @JoinColumn(name = "competition_id", nullable = false, insertable = true, updatable = true)
    private Competition competition;

}


