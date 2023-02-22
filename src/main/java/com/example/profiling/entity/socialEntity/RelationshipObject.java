package com.example.profiling.entity.socialEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@Table(name = "relationship_object")
public class RelationshipObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer relationshipId;

    private String relationshipName;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "account_id")
    private Account account;

    public RelationshipObject() {

    }
}
