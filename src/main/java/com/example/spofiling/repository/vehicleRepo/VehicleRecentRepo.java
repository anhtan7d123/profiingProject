package com.example.spofiling.repository.vehicleRepo;

import com.example.spofiling.entity.vehicleEntity.VehicleRecentLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRecentRepo extends JpaRepository<VehicleRecentLocation, Integer> {
}
