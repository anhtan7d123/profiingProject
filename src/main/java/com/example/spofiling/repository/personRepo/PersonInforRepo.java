package com.example.spofiling.repository.personRepo;

import com.example.spofiling.entity.personEntity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonInforRepo extends JpaRepository<PersonInfor, Integer> {
    PersonInfor findByPersonName(String name);

    PersonInfor findByPersonId(Integer id);

    @Query("select i from PersonInfor i order by i.levelPopular")
    List<PersonInfor> findAllOrderByLevelPopular();



//    List<InforPerson> findByPersonIdOrPersonNameOrPhonesContainingOrEmailsContainingOrImagesContainingOrLocationsContainingOrWorkExperiencesContainingOrVehiclesContainingOrImagesContainingOrLevelPopular(
//            Integer personId,
//            String personName,
//            List<Phone> phones,
//            List<Email> emails,
//            List<Image> images,
//            List<Location> locations,
//            List<WorkExperience> workExperiences,
//            List<VehicleInfor> vehicles,
//            List<ItemInfor> items,
//            Integer levelPopular
//    );
}
