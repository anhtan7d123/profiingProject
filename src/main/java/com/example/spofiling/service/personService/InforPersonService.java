package com.example.spofiling.service.personService;

import com.example.spofiling.repository.personRepo.InforPersonRepo;
import org.springframework.stereotype.Service;

@Service
public class InforPersonService {
    private final InforPersonRepo inforPersonRepo;

    public InforPersonService(InforPersonRepo inforPersonRepo) {
        this.inforPersonRepo = inforPersonRepo;
    }

}
