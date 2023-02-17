package com.example.spofiling.service.personService;

import com.example.spofiling.entity.personEntity.PersonInfor;
import com.example.spofiling.entity.personEntity.Phone;
import com.example.spofiling.repository.personRepo.PersonInforRepo;
import com.example.spofiling.repository.personRepo.PhoneRepo;
import org.springframework.stereotype.Service;

@Service
public class PersonInforService {
    private final PersonInforRepo personInforRepo;

    private final PhoneRepo phoneRepo;

    public PersonInforService(PersonInforRepo personInforRepo, PhoneRepo phoneRepo) {
        this.personInforRepo = personInforRepo;
        this.phoneRepo = phoneRepo;
    }

    public void addPhoneToPerson(String personName, String phoneNumber){
        PersonInfor person = personInforRepo.findByPersonName(personName);
        Phone phone = phoneRepo.findByPhoneNumber(phoneNumber);
//        person.setPhones(Arrays.asList(phone));
//        inforPersonRepo.save(person);
        phone.setPersonInfor(person);
        phoneRepo.save(phone);
    }
}
