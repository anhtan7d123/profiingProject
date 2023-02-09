package com.example.spofiling.repository.personRepo;

import com.example.spofiling.entity.personEntity.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkExRepo extends JpaRepository<WorkExperience, Integer> {
}
