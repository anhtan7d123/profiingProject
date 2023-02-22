package com.example.profiling.repository.personRepo;

import com.example.profiling.entity.personEntity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends JpaRepository<Image, Integer> {
    Image findByImageId(Integer imageId);
}
