package com.example.spofiling.entity.personEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@Table(name = "localtion_of_person")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer locationId;

    private String localtionName;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "person_id")
    private InforPerson inforPerson;

    public Location() {

    }
}
