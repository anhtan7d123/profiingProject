package com.example.spofiling.repository.personRepo;

import com.example.spofiling.entity.personEntity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepo extends JpaRepository<Email, Integer> {
    Email findByEmailId(Integer emailId);
}
