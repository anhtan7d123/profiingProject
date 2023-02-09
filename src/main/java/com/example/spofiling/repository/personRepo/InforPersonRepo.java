package com.example.spofiling.repository.personRepo;

import com.example.spofiling.entity.personEntity.InforPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InforPersonRepo extends JpaRepository<InforPerson, Integer> {
}
