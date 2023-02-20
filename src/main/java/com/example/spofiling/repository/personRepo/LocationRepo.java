package com.example.spofiling.repository.personRepo;

import com.example.spofiling.entity.personEntity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepo extends JpaRepository<Location, Integer> {
    Location findByLocationId(Integer locationId);
}
