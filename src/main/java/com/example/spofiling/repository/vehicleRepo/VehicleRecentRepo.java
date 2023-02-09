package com.example.spofiling.repository.vehicleRepo;

import com.example.spofiling.entity.vehicleEntity.VehicleRecentLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRecentRepo extends JpaRepository<VehicleRecentLocation, Integer> {
}
