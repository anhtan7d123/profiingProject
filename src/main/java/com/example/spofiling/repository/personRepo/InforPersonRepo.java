package com.example.spofiling.repository.personRepo;

import com.example.spofiling.entity.personEntity.InforPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InforPersonRepo extends JpaRepository<InforPerson, Integer> {
    InforPerson findByPersonName(String personName);


    @Query("select i from InforPerson i order by i.levelPopular")
    List<InforPerson> findAllOrderByLevelPopular();

//    @Query("select i from InforPerson i where i.personId = ?1 or i.personName = ?2")
//    List<InforPerson> findAllByFilter(Integer personId, String personName);

//    @Query(value = x, nativeQuery = true)
//    List<InforPerson> findAllByFilters(Integer personId, String personName);


}
