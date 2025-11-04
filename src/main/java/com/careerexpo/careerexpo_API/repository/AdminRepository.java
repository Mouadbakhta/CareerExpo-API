package com.careerexpo.careerexpo_API.repository;
import com.careerexpo.careerexpo_API.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByEmail(String email);
    Admin findByPassword(String password);
    Admin findByEmailAndPassword(String email, String password);


}
