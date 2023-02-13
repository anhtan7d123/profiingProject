package com.example.spofiling.api;

import com.example.spofiling.entity.personEntity.InforPerson;
import com.example.spofiling.entity.personEntity.Phone;
import com.example.spofiling.repository.personRepo.InforPersonRepo;
import com.example.spofiling.repository.personRepo.PhoneRepo;
import com.example.spofiling.service.personService.InforPersonService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/person")
public class PersonController {

    private final InforPersonRepo inforPersonRepo;

    private final PhoneRepo phoneRepo;

    private final InforPersonService inforPersonService;

    @PostMapping("/create-person-infor")
    public ResponseEntity<?> createPersonInfor(@RequestBody InforPerson inforPerson){
        InforPerson checkPerson = inforPersonRepo.findByPersonName(inforPerson.getPersonName());
        if (checkPerson != null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        else {
            log.info("Tên chưa tồn tại");
            InforPerson newPerson = inforPersonRepo.save(inforPerson);
            return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<InforPerson>> getAllPersonInfor(){
//        List<InforPerson> list = inforPersonRepo.findAllOrderByLevelPopular(0);
        List<InforPerson> list = new ArrayList<>();
        inforPersonRepo.findAll().forEach(list::add);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //Hien thi profile theo muc do quan tam
    @GetMapping("/get-all/get-by-level")
    public ResponseEntity<List<InforPerson>> getAllPersonInforByLevel(){
        List<InforPerson> list = new ArrayList<>();
        inforPersonRepo.findAllOrderByLevelPopular().forEach(list::add);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @PostMapping("/add-phone")
    public ResponseEntity<?> addPhoneToPerson(@RequestBody PhoneToPerForm phoneForm){
        String personName = phoneForm.getPersonName();
        String phoneNumber = phoneForm.getPhoneNumber();
        InforPerson person = inforPersonRepo.findByPersonName(personName);
        Phone phone = phoneRepo.findByPhoneNumber(phoneNumber);
        boolean check = person.getPhones().contains(phone);
        if (!check){
            inforPersonService.addPhoneToPerson(personName,phoneNumber);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }else {
            log.error("phone da ton tai");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }
}

@Data class PhoneToPerForm{
    private String personName;

    private String phoneNumber;
}
