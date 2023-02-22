package com.example.profiling.repository.personRepo;

import com.example.profiling.entity.personEntity.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkExRepo extends JpaRepository<WorkExperience, Integer> {
    WorkExperience findByWorkId(Integer workExId);
}
