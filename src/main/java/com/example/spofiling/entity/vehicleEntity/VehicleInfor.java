package com.example.spofiling.entity.vehicleEntity;

import com.example.spofiling.entity.personEntity.PersonInfor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Table(name = "vehicle_information")
public class VehicleInfor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer vehicleId;

    private String vehicleName;

    private String vehicleLabel;

    private String vehicleNumber;

    private String vehicleSize;

    private String vehicleColor;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "person_id")
    private PersonInfor vehicleOwner;

    @OneToMany(mappedBy = "vehicleInfor")
    @JsonIgnore
    private List<VehicleRecentLocation> vehicleRecentLocations;

    public VehicleInfor() {

    }
}
