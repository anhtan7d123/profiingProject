package com.example.spofiling.api;

import com.example.spofiling.entity.personEntity.PersonInfor;
import com.example.spofiling.entity.vehicleEntity.VehicleInfor;
import com.example.spofiling.entity.vehicleEntity.VehicleRecentLocation;
import com.example.spofiling.repository.personRepo.PersonInforRepo;
import com.example.spofiling.repository.vehicleRepo.VehicleInforRepo;
import com.example.spofiling.repository.vehicleRepo.VehicleRecentRepo;
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

//    @PostMapping("/task/createTask")
//    public ResponseEntity<?> requestTask(@RequestBody Task pTask) {
//        try {
//            List<Sample> listSample = pTask.getSamples();
//            if (listSample != null) {
//                for (Sample sample : listSample) {
//                    Sample findSample = sampleService.getSampleById(sample.getId());
//                    if (findSample == null) {
//                        Map<String, Object> bodySuccess = responseService.responseBody(404, "Sample with id = " + sample.getId() + " doest not exists.", null);
//                        return new ResponseEntity<>(bodySuccess, HttpStatus.NOT_FOUND);
//                    }
//                }
//                Task task = taskService.requestTask(pTask);
//                Map<String, Object> bodySuccess = responseService.responseBody(200, "Send request task success.", task);
//                template.send(ncsConfig.getKafka().getTopicname(), task);
//                System.out.println("Send task to kafka success");
//                return new ResponseEntity<>(bodySuccess, HttpStatus.OK);
//            } else {
//                Map<String, Object> bodySuccess = responseService.responseBody(404, "List sample is null", null);
//                return new ResponseEntity<>(bodySuccess, HttpStatus.NOT_FOUND);
//            }
//
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

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
