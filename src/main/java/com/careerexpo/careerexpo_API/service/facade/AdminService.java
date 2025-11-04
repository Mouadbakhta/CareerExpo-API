package com.careerexpo.careerexpo_API.service.facade;

import com.careerexpo.careerexpo_API.entity.Admin;
import java.util.List;
import java.util.Optional;

public interface AdminService {
    Admin createAdmin(Admin admin);
    Optional<Admin> getAdminById(Long id);
    Optional<Admin> getAdminByEmail(String email);
    List<Admin> getAllAdmins();
    Admin updateAdmin(Long id, Admin admin);
    void deleteAdmin(Long id);
    boolean existsByEmail(String email);
    boolean validateAdmin(Admin admin);
}
