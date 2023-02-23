package com.example.profiling.api;

import com.example.profiling.entity.vehicleEntity.VehicleInfor;
import com.example.profiling.repository.vehicleRepo.VehicleInforRepo;
import com.example.profiling.repository.vehicleRepo.VehicleRecentRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/vehicle")
public class VehicleController {

    private final VehicleInforRepo vehicleInforRepo;

    private final VehicleRecentRepo vehicleRecentRepo;

    @PostMapping("/create-vehicle")
    public ResponseEntity<?> createVehicleInfor(@RequestBody VehicleInfor vehicleInfor){
        VehicleInfor checkVehicle = vehicleInforRepo.findByVehicleName(vehicleInfor.getVehicleName());
        if (checkVehicle != null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else {
            vehicleInforRepo.save(vehicleInfor);
            return new ResponseEntity<>(vehicleInfor, HttpStatus.CREATED);
        }
    }


    @GetMapping("/get-by-keywork/{keyword}")
    public ResponseEntity<?> getVehicleByAllPro(@PathVariable String keyword){
        List<VehicleInfor> vehicleInfors = new ArrayList<>();
        vehicleInforRepo.findByAllProperties(keyword).forEach(vehicleInfors::add);
        return new ResponseEntity<>(vehicleInfors, HttpStatus.OK);
    }
}
