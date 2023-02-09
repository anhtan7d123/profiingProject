package com.example.spofiling.api;

import com.example.spofiling.entity.personEntity.InforPerson;
import com.example.spofiling.repository.personRepo.InforPersonRepo;
import com.example.spofiling.service.personService.InforPersonService;
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

    private final InforPersonRepo inforPersonRepo;

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
        return new ResponseEntity<>(inforPersonRepo.findAllOrderByLevelPopular(0), HttpStatus.OK);
    }

}
