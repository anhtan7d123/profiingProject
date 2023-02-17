package com.example.spofiling.api;

import com.example.spofiling.entity.personEntity.PersonInfor;
import com.example.spofiling.entity.personEntity.Phone;
import com.example.spofiling.entity.personEntity.WorkExperience;
import com.example.spofiling.entity.vehicleEntity.VehicleInfor;
import com.example.spofiling.repository.personRepo.PersonInforRepo;
import com.example.spofiling.repository.personRepo.PhoneRepo;
import com.example.spofiling.repository.vehicleRepo.VehicleInforRepo;
import com.example.spofiling.service.personService.PersonInforService;
import lombok.Data;
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
@RequestMapping("/api/person")
public class PersonController {

    private final PersonInforRepo personInforRepo;

    private final PersonInforService personInforService;

    private final PhoneRepo phoneRepo;

    private final VehicleInforRepo vehicleInforRepo;

    @PostMapping("/create-phone")
    public ResponseEntity<?> createPhone(@RequestBody Phone phone){
        Phone checkPhone = phoneRepo.findByPhoneNumber(phone.getPhoneNumber());
        if (checkPhone != null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else {
            Phone newPhone = phoneRepo.save(phone);
            return new ResponseEntity<>(newPhone, HttpStatus.CREATED);
        }
    }

    //Them sdt
    @PostMapping("/add-phone")
    public ResponseEntity<?> addPhoneToPerson(@RequestBody PhoneToPerForm phoneForm){
        String personName = phoneForm.getPersonName();
        String phoneNumber = phoneForm.getPhoneNumber();
        PersonInfor person = personInforRepo.findByPersonName(personName);
        Phone phone = phoneRepo.findByPhoneNumber(phoneNumber);
        boolean check = person.getPhones().contains(phone);
        if (!check){
            personInforService.addPhoneToPerson(personName,phoneNumber);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }else {
            log.error("phone da ton tai");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

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
    //Tao 1 doi tuong person moi
    @PostMapping("/create-infor")
    public ResponseEntity<?> createPersonInfor(@RequestBody PersonInfor personInfor){
        PersonInfor checkPerson = personInforRepo.findByPersonName(personInfor.getPersonName());
        if (checkPerson != null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        else {
            log.info("Tên chưa tồn tại");
            List<VehicleInfor> vehicleInfors = personInfor.getVehicles();
            if (vehicleInfors != null){
                List<VehicleInfor> newVehicles = new ArrayList<>();
                for (VehicleInfor vehicleInfor : vehicleInfors){
                    VehicleInfor checkVehicle = vehicleInforRepo.
                            findByVehicleId(vehicleInfor.getVehicleId());
                    if (checkVehicle != null){
                        personInforRepo.save(personInfor);
                        checkVehicle.setVehicleOwner(personInfor);
                        vehicleInforRepo.save(checkVehicle);
                    }
                }
            }
            PersonInfor newPer = personInforRepo.findByPersonId(personInfor.getPersonId());
            return new ResponseEntity<>(newPer, HttpStatus.CREATED);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<PersonInfor>> getAllPersonInfor(){
//        List<InforPerson> list = inforPersonRepo.findAllOrderByLevelPopular(0);
        List<PersonInfor> list = new ArrayList<>();
        personInforRepo.findAll().forEach(list::add);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //Hien thi all profile theo muc do quan tam
    @GetMapping("/get-all/get-by-level")
    public ResponseEntity<List<PersonInfor>> getAllPersonInforByLevel(){
        List<PersonInfor> list = new ArrayList<>();
        personInforRepo.findAllOrderByLevelPopular().forEach(list::add);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //Hien thi danh sach noi lam viec theo id person
    @GetMapping("/get-work-by-id/{id}")
    public ResponseEntity<?> getWorkExperiencesById(@PathVariable Integer id){
        PersonInfor checkPer = personInforRepo.findByPersonId(id);
        if (checkPer != null){
            List<WorkExperience> listWork = new ArrayList<>();
            checkPer.getWorkExperiences().forEach(listWork::add);
            return new ResponseEntity<>(listWork, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}

@Data class PhoneToPerForm{
    private String personName;

    private String phoneNumber;
}
