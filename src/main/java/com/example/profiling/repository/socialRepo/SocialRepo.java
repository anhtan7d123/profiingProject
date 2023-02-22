package com.example.profiling.repository.socialRepo;

import com.example.profiling.entity.socialEntity.Social;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialRepo extends JpaRepository<Social, Integer> {
    Social findBySocialId(Integer socialId);

    Social findBySocialName(String socialName);
}
