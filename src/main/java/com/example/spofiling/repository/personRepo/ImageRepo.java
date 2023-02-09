package com.example.spofiling.repository.personRepo;

import com.example.spofiling.entity.personEntity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<Image, Integer> {
}
