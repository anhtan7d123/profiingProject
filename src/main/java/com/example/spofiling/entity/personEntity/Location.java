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
    //20.98339503454848, 105.81614662970462
    private Double latitude;

    private Double longitude;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "person_id")
    private PersonInfor personInfor;

    public Location() {

    }
}
