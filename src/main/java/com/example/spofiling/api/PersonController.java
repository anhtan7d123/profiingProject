package com.example.spofiling.api;

import com.example.spofiling.entity.itemEntity.ItemInfor;
import com.example.spofiling.entity.personEntity.*;
import com.example.spofiling.entity.vehicleEntity.VehicleInfor;
import com.example.spofiling.repository.itemRepo.ItemInforRepo;
import com.example.spofiling.repository.personRepo.*;
import com.example.spofiling.repository.vehicleRepo.VehicleInforRepo;
import com.example.spofiling.service.personService.PersonInforService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/person")
public class PersonController {

    private final PersonInforRepo personInforRepo;

    private final PersonInforService personInforService;

    private final PhoneRepo phoneRepo;

    private final EmailRepo emailRepo;

    private final ImageRepo imageRepo;

    private final LocationRepo locationRepo;

    private final WorkExRepo workExRepo;
    private final VehicleInforRepo vehicleInforRepo;

    private final ItemInforRepo itemInforRepo;

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
    
    //Tao 1 doi tuong person moi
    @PostMapping("/create-infor")
    public ResponseEntity<?> createPersonInfor(@RequestBody PersonInfor personInfor){
        PersonInfor checkPerson = personInforRepo.findByPersonName(personInfor.getPersonName());
        if (checkPerson != null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        else {
            boolean checkRequest = true;
            log.info("Tên chưa tồn tại");
            Map<String, String> newBody = new LinkedHashMap<>();
            personInforRepo.save(personInfor);
            List<Phone> phones = personInfor.getPhones();
            List<Email> emails = personInfor.getEmails();
            List<Image> images = personInfor.getImages();
            List<Location> locations = personInfor.getLocations();
            List<WorkExperience> workExperiences = personInfor.getWorkExperiences();
            List<VehicleInfor> vehicleInfors = personInfor.getVehicleInfors();
            List<ItemInfor> itemInfors = personInfor.getItemInfors();

            if (phones != null){
                for (Phone phone : phones){
                    Phone checkPhone = phoneRepo.
                            findByPhoneId(phone.getPhoneId());
                    if (checkPhone != null ){
                        if (checkPhone.getPersonInfor() == null){
                            checkPhone.setPersonInfor(personInfor);
                            phoneRepo.save(checkPhone);
                        }
                        else {
                            checkRequest = false;
                            newBody.put("Error: ", "phoneId: " + checkPhone.getPhoneId() + " already owned");
                        }
                    }
                }
            }

            if (emails != null){
                for (Email email : emails){
                    Email checkEmail = emailRepo.findByEmailId(email.getEmailId());
                    if (checkEmail != null){
                        if (checkEmail.getPersonInfor() == null){
                            checkEmail.setPersonInfor(personInfor);
                            emailRepo.save(checkEmail);
                        }
                        else {
                            checkRequest = false;
                            newBody.put("Error: ", "emailId: " + checkEmail.getEmailId() + " already owned");
                        }
                    }
                }
            }

            if (images != null){
                for (Image image : images){
                    Image checkImage = imageRepo.findByImageId(image.getImageId());
                    if (checkImage != null){
                        if (checkImage.getPersonInfor() == null){
                            checkImage.setPersonInfor(personInfor);
                            imageRepo.save(checkImage);
                        }
                        else {
                            checkRequest = false;
                            newBody.put("Error: ", "imageId: " + checkImage.getImageId() + " already owned");
                        }
                    }
                }
            }

            if (locations != null){
                for (Location location : locations){
                    Location checkLocation = locationRepo.findByLocationId(location.getLocationId());
                    if (checkLocation != null){
                        if (checkLocation.getPersonInfor() == null){
                            checkLocation.setPersonInfor(personInfor);
                            locationRepo.save(checkLocation);
                        }
                        else {
                            checkRequest = false;
                            newBody.put("Error: ", "locationId: " + checkLocation.getLocationId() + " already owned");
                        }
                    }
                }
            }

            if (workExperiences != null){
                for (WorkExperience workExperience : workExperiences){
                    WorkExperience checkWorkExperience = workExRepo.findByWorkId(workExperience.getWorkId());
                    if (checkWorkExperience != null){
                        if (checkWorkExperience.getPersonInfor() == null){
                            checkWorkExperience.setPersonInfor(personInfor);
                            workExRepo.save(checkWorkExperience);
                        }
                        else {
                            checkRequest = false;
                            newBody.put("Error: ", "WorkId: " + checkWorkExperience.getWorkId() + " already owned");
                        }
                    }
                }
            }

            if (vehicleInfors != null){
//                List<VehicleInfor> newVehicles = new ArrayList<>();
                for (VehicleInfor vehicleInfor : vehicleInfors){
                    VehicleInfor checkVehicle = vehicleInforRepo.
                            findByVehicleId(vehicleInfor.getVehicleId());
                    if (checkVehicle != null){
                        if (checkVehicle.getVehicleOwner() == null){
                            checkVehicle.setVehicleOwner(personInfor);
                            vehicleInforRepo.save(checkVehicle);
                        }
                        else {
                            checkRequest = false;
                            newBody.put("error: ", "vehicleId: " + checkVehicle.getVehicleId() + " already owned");
                        }
                    }
                }
            }
            if (checkRequest == true){
                PersonInfor newPer = personInforRepo.findByPersonId(personInfor.getPersonId());
                return new ResponseEntity<>(newPer, HttpStatus.CREATED);
            }
            else {
                personInforRepo.deleteById(personInfor.getPersonId());
                return new ResponseEntity<>(newBody, HttpStatus.BAD_REQUEST);
            }
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
