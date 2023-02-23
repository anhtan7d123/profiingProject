package com.example.profiling.entity.addressEntity;

import com.example.profiling.entity.itemEntity.ItemInfor;
import com.example.profiling.entity.vehicleEntity.VehicleInfor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Table(name = "recent_location_list")
public class RecentLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer recentLocationId;

    private String recentLocationName;

    private Double latitude;

    private Double longitude;

    private Long timeStart;

    private Long timeEnd;

    @ManyToMany(mappedBy = "itemRecentLocations")
    @JsonIgnore
    private List<ItemInfor> itemInfors;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "vehicle_id")
    private VehicleInfor vehicleInfor;

    public RecentLocation() {

    }
}
