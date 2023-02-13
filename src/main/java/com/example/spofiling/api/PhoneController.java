package com.example.spofiling.api;

import com.example.spofiling.entity.personEntity.Phone;
import com.example.spofiling.repository.personRepo.PhoneRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/phone")
public class PhoneController {

    private final PhoneRepo phoneRepo;

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
}
