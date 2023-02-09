package com.example.spofiling.repository.personRepo;

import com.example.spofiling.entity.personEntity.InforPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InforPersonRepo extends JpaRepository<InforPerson, Integer> {
    InforPerson findByPersonName(String personName);

    List<InforPerson> findAllOrderByLevelPopular(Integer levelPopular);

}
