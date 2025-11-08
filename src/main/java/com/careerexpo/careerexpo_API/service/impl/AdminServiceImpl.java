package com.careerexpo.careerexpo_API.service.impl;

import com.careerexpo.careerexpo_API.entity.Admin;
import com.careerexpo.careerexpo_API.repository.AdminRepository;
import com.careerexpo.careerexpo_API.service.facade.AdminService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminServiceImpl(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public Admin createAdmin(Admin admin) {
        if (!validateAdmin(admin)) {
            throw new IllegalArgumentException("Données admin invalides");
        }

        if (existsByEmail(admin.getEmail())) {
            throw new IllegalArgumentException("Email déjà utilisé");
        }

        // Encoder le mot de passe avant de sauvegarder
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        return adminRepository.save(admin);
    }

    @Override
    public Optional<Admin> getAdminById(Long id) {
        return adminRepository.findById(id);
    }

    @Override
    public Optional<Admin> getAdminByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    @Transactional
    public Admin updateAdmin(Long id, Admin admin) {
        Admin existingAdmin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin non trouvé avec l'id: " + id));

        // Mettre à jour l'email si différent
        if (!existingAdmin.getEmail().equals(admin.getEmail())) {
            if (existsByEmail(admin.getEmail())) {
                throw new IllegalArgumentException("Email déjà utilisé");
            }
            existingAdmin.setEmail(admin.getEmail());
        }

        // Mettre à jour le mot de passe si fourni
        if (admin.getPassword() != null && !admin.getPassword().isEmpty()) {
            existingAdmin.setPassword(passwordEncoder.encode(admin.getPassword()));
        }

        // Mettre à jour le rôle
        if (admin.getRole() != null) {
            existingAdmin.setRole(admin.getRole());
        }

        return adminRepository.save(existingAdmin);
    }

    @Override
    @Transactional
    public void deleteAdmin(Long id) {
        if (!adminRepository.existsById(id)) {
            throw new RuntimeException("Admin non trouvé avec l'id: " + id);
        }
        adminRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return adminRepository.existsByEmail(email);
    }

    @Override
    public boolean validateAdmin(Admin admin) {
        if (admin == null) {
            return false;
        }

        if (admin.getEmail() == null || admin.getEmail().isBlank()) {
            return false;
        }

        if (admin.getPassword() == null || admin.getPassword().length() < 6) {
            return false;
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!admin.getEmail().matches(emailRegex)) {
            return false;
        }

        return true;
    }
}