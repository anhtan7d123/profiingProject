package com.example.spofiling.repository.personRepo;

import com.example.spofiling.entity.personEntity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepo extends JpaRepository<Phone, Integer> {
}
