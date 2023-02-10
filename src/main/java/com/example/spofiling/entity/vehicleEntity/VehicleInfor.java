package com.example.spofiling.entity.vehicleEntity;

import com.example.spofiling.entity.personEntity.InforPerson;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

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
    @JoinColumn(name = "person_id")
    private InforPerson vehicleOwner;

    @OneToMany(mappedBy = "vehicleInfor")
    private Collection<VehicleRecentLocation> vehicleRecentLocations;

    public VehicleInfor() {

    }
}
