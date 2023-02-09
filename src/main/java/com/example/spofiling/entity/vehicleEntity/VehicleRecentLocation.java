package com.example.spofiling.entity.vehicleEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@Table(name = "vehicle_recent_location")
public class VehicleRecentLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer recentLocationId;

    private String recentLocationName;

    private Date recentLocationStart;

    private Date recentLocationEnd;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private VehicleInfor vehicleInfor;

    public VehicleRecentLocation() {

    }
}
