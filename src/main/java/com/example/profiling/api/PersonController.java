package com.example.profiling.api;

import com.example.profiling.entity.itemEntity.ItemInfor;
import com.example.profiling.entity.personEntity.*;
import com.example.profiling.entity.socialEntity.Account;
import com.example.profiling.entity.socialEntity.Social;
import com.example.profiling.entity.vehicleEntity.VehicleInfor;
import com.example.profiling.repository.itemRepo.ItemInforRepo;
import com.example.profiling.repository.personRepo.*;
import com.example.profiling.repository.socialRepo.SocialRepo;
import com.example.profiling.repository.vehicleRepo.VehicleInforRepo;
import com.example.profiling.service.personService.PersonInforService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;


import java.util.*;


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

    private final SocialRepo socialRepo;

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
//            boolean checkRequest = true;
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
            List<Social> socials = personInfor.getSocials();

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
                            newBody.put("Error: ", "phoneId: " + checkPhone.getPhoneId() + " already owned");
                        }
                    }else {
                        newBody.put("Error: ", "phoneId: " + phone.getPhoneId() + " does not exist");
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
                            newBody.put("Error: ", "emailId: " + checkEmail.getEmailId() + " already owned");
                        }
                    }else {
                        newBody.put("Error: ", "emailId: " + email.getEmailId() + " does not exist");
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
                            newBody.put("Error: ", "imageId: " + checkImage.getImageId() + " already owned");
                        }
                    }else {
                        newBody.put("Error: ", "imageId: " + image.getImageId() + " does not exist");
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
                            newBody.put("Error: ", "locationId: " + checkLocation.getLocationId() + " already owned");
                        }
                    }else {
                        newBody.put("Error: ", "locationId: " + location.getLocationId() + " does not exist");
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
                            newBody.put("Error: ", "WorkId: " + checkWorkExperience.getWorkId() + " already owned");
                        }
                    }else {
                        newBody.put("Error: ", "WorkId: " + workExperience.getWorkId() + " does not exist");
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
                            newBody.put("error: ", "vehicleId: " + checkVehicle.getVehicleId() + " already owned");
                        }
                    }else {
                        newBody.put("error: ", "vehicleId: " + vehicleInfor.getVehicleId() + " does not exist");
                    }
                }
            }

            if (itemInfors != null){
                for (ItemInfor itemInfor : itemInfors){
                    ItemInfor checkItem = itemInforRepo.findByItemId(itemInfor.getItemId());
                    if (checkItem != null){
                        if (checkItem.getItemOwner() == null){
                            checkItem.setItemOwner(personInfor);
                            itemInforRepo.save(checkItem);
                        }else {
                            newBody.put("error: ", "itemId: " + checkItem.getItemId() + " already owned");
                        }
                    }else {
                        newBody.put("error: ", "itemId: " + itemInfor.getItemId() + " does not exist");
                    }
                }
            }

            if (socials != null){
                for (Social social : socials){
                    Social checkSocial = socialRepo.findBySocialId(social.getSocialId());
                    if (checkSocial != null){
                        if (checkSocial.getPersonInfor() == null){
                            checkSocial.setPersonInfor(personInfor);
                            socialRepo.save(checkSocial);
                        }else {
                            newBody.put("error: ", "socialId: " + checkSocial.getSocialId() + " already owned");
                        }
                    }else {
                        newBody.put("error: ", "socialId: " + social.getSocialId() + " does not exist");
                    }
                }
            }
            if (newBody.isEmpty()){
                PersonInfor newPersonInfor = personInforRepo.findByPersonId(personInfor.getPersonId());
                return new ResponseEntity<>(newPersonInfor, HttpStatus.CREATED);
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

    @GetMapping("get-contact-by-id/{id}")
    public ResponseEntity<?> getContactsById(@PathVariable Integer id){
        PersonInfor checkPer = personInforRepo.findByPersonId(id);
        if (checkPer != null){
            List<Phone> contacts = new ArrayList<>();
            checkPer.getPhones().forEach(contacts::add);
            return new ResponseEntity<>(contacts, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //lay danh sach danh ba chia theo 2 loai potential va verified
    @GetMapping("get-contact-for-rate/{id}")
    public ResponseEntity<?> getContactsForRate(@PathVariable Integer id){
        PersonInfor checkPer = personInforRepo.findByPersonId(id);
        if (checkPer != null){
            List<Phone> defaultContacts = checkPer.getPhones();
            List<Phone> verifiedContacts = new ArrayList<>();
            List<Phone> potentialContacts = new ArrayList<>();
            for (Phone phone : defaultContacts){
                if (phone.getPhoneRate() == 100){
                    verifiedContacts.add(phone);
                }
                else {
                    potentialContacts.add(phone);
                }
            }
            Map<String, Object> newBody = new LinkedHashMap<>();
            newBody.put("potential contacts: ", potentialContacts);
            newBody.put("verified contacts: ", verifiedContacts);
            return new ResponseEntity<>(newBody, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-location-by-id/{id}")
    public ResponseEntity<?> getLocationById(@PathVariable Integer id){
        PersonInfor checkPer = personInforRepo.findByPersonId(id);
        if (checkPer != null){
            List<Location> locations = new ArrayList<>();
            checkPer.getLocations().forEach(locations::add);
            return new ResponseEntity<>(locations, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    //Lay ta ca cac account mxh cua person
    @GetMapping("/get-account-by-id/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable Integer id){
        PersonInfor checkPer = personInforRepo.findByPersonId(id);
        if (checkPer != null){
            List<Account> accountList = new ArrayList<>();
            List<Social> socials = checkPer.getSocials();
            for (Social social : socials){
                List<Account> accounts = social.getAccounts();
                accounts.forEach(accountList::add);
            }
            return new ResponseEntity<>(accountList, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}

@Data class PhoneToPerForm{
    private String personName;

    private String phoneNumber;
}
