package com.example.spofiling.repository.personRepo;

import com.example.spofiling.entity.personEntity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepo extends JpaRepository<Email, Integer> {
}
