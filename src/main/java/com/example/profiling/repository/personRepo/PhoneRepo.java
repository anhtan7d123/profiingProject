package com.example.profiling.repository.personRepo;

import com.example.profiling.entity.personEntity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepo extends JpaRepository<Phone, Integer> {
    Phone findByPhoneNumber(String phoneNumber);

    Phone findByPhoneId(Integer id);

}
