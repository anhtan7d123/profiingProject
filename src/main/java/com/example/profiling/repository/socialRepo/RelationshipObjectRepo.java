package com.example.profiling.repository.socialRepo;

import com.example.profiling.entity.socialEntity.RelationshipObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationshipObjectRepo extends JpaRepository<RelationshipObject, Integer> {
    RelationshipObject findByRelationshipId(Integer relationshipId);

    RelationshipObject findByRelationshipName(String relationshipName);
}
