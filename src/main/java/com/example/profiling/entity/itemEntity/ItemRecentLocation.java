package com.example.profiling.entity.itemEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Table(name = "item_recent_location")
public class ItemRecentLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer recentItemId;

    private String recentItemName;

    private Long recentItemStart;

    private Long recentItemEnd;

    @ManyToMany(mappedBy = "itemRecentLocations")
    @JsonIgnore
    private List<ItemInfor> itemInfors;

    public ItemRecentLocation() {

    }
}
