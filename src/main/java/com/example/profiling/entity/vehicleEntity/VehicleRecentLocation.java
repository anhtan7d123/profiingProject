package com.example.profiling.entity.vehicleEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private Long recentLocationStart;

    private Long recentLocationEnd;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "vehicle_id")
    private VehicleInfor vehicleInfor;

    public VehicleRecentLocation() {

    }
}
