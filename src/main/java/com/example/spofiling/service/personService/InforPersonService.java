package com.example.spofiling.service.personService;

import com.example.spofiling.entity.personEntity.InforPerson;
import com.example.spofiling.entity.personEntity.Phone;
import com.example.spofiling.repository.personRepo.InforPersonRepo;
import com.example.spofiling.repository.personRepo.PhoneRepo;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class InforPersonService {
    private final InforPersonRepo inforPersonRepo;

    private final PhoneRepo phoneRepo;

    public InforPersonService(InforPersonRepo inforPersonRepo, PhoneRepo phoneRepo) {
        this.inforPersonRepo = inforPersonRepo;
        this.phoneRepo = phoneRepo;
    }

    public void addPhoneToPerson(String personName, String phoneNumber){
        InforPerson person = inforPersonRepo.findByPersonName(personName);
        Phone phone = phoneRepo.findByPhoneNumber(phoneNumber);
//        person.setPhones(Arrays.asList(phone));
//        inforPersonRepo.save(person);
        phone.setInforPerson(person);
        phoneRepo.save(phone);
    }
}
