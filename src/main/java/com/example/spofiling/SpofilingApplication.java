package com.example.spofiling;

import com.example.spofiling.entity.personEntity.InforPerson;
import com.example.spofiling.entity.personEntity.Phone;
import com.example.spofiling.repository.personRepo.InforPersonRepo;
import com.example.spofiling.repository.personRepo.PhoneRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class SpofilingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpofilingApplication.class, args);
	}

}
