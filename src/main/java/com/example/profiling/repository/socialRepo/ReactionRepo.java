package com.example.profiling.repository.socialRepo;

import com.example.profiling.entity.socialEntity.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionRepo extends JpaRepository<Reaction, Integer> {
    Reaction findByReactionId(Integer reactionId);

    Reaction findByReactionName(String reactionName);
}
