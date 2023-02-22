package com.example.profiling.repository.vehicleRepo;

import com.example.profiling.entity.vehicleEntity.VehicleRecentLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRecentRepo extends JpaRepository<VehicleRecentLocation, Integer> {
    VehicleRecentLocation findByRecentLocationId(Integer recentLocation);

    VehicleRecentLocation findByRecentLocationName(String recentLocationName);
}
