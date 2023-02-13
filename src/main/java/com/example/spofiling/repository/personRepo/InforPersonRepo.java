package com.example.spofiling.repository.personRepo;

import com.example.spofiling.entity.itemEntity.ItemInfor;
import com.example.spofiling.entity.personEntity.*;
import com.example.spofiling.entity.vehicleEntity.VehicleInfor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InforPersonRepo extends JpaRepository<InforPerson, Integer> {
    InforPerson findByPersonName(String name);

    @Query("select i from InforPerson i order by i.levelPopular")
    List<InforPerson> findAllOrderByLevelPopular();


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
