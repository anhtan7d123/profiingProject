package com.example.spofiling.repository.vehicleRepo;

import com.example.spofiling.entity.vehicleEntity.VehicleInfor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleInforRepo extends JpaRepository<VehicleInfor, Integer> {
}
