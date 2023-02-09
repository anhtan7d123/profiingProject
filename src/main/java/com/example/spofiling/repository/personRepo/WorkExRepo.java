package com.example.spofiling.repository.personRepo;

import com.example.spofiling.entity.personEntity.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkExRepo extends JpaRepository<WorkExperience, Integer> {
}
