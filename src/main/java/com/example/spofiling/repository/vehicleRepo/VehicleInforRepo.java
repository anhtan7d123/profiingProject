package com.example.spofiling.repository.vehicleRepo;

import com.example.spofiling.entity.vehicleEntity.VehicleInfor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleInforRepo extends JpaRepository<VehicleInfor, Integer> {

    VehicleInfor findByVehicleId(Integer id);

    VehicleInfor findByVehicleName(String name);

    @Query("select v from VehicleInfor v where " +
            "v.vehicleName = ?1 " +
            "or v.vehicleNumber = ?1 " +
            "or v.vehicleLabel = ?1 " +
            "or v.vehicleSize = ?1 " +
            "or v.vehicleColor = ?1")
    List<VehicleInfor> findByAllProperties(String keywork);
}
