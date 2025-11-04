package com.careerexpo.careerexpo_API;

import com.careerexpo.careerexpo_API.entity.Admin;
import com.careerexpo.careerexpo_API.service.facade.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CareerExpoApiApplication {

    private static final Logger log = LoggerFactory.getLogger(CareerExpoApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CareerExpoApiApplication.class, args);
        log.info("✓ Application Career Expo API démarrée avec succès");
    }

   @Bean
   public CommandLineRunner initializeDefaultAdmin(AdminService adminService) {
       return args -> {
           try {
               if (adminService.getAllAdmins().isEmpty()) {
                   log.info("Aucun admin détecté. Création d'un admin par défaut...");

                   Admin defaultAdmin = new Admin();
                   defaultAdmin.setEmail("admin@careerexpo.com");
                   defaultAdmin.setPassword("admin123"); // should be encoded in AdminService
                   defaultAdmin.setRole("ADMIN");

                   adminService.createAdmin(defaultAdmin);

                   log.info("✓ Admin par défaut créé avec succès");
                   log.info("  Email : admin@careerexpo.com");
                   log.info("  Rôle : ADMIN");
                   log.info("  ⚠️ IMPORTANT : changez ce mot de passe avant la mise en production !");
               } else {
                   log.info("✓ Admin(s) déjà présent(s) dans la base de données");
               }
           } catch (Exception e) {
               log.warn("⚠️ Erreur lors de l'initialisation de l'admin par défaut : {}", e.getMessage());
               log.warn("Créez un admin manuellement via l'API si nécessaire.");
           }
       };
   }
}
