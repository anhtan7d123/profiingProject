package com.example.spofiling.entity.itemEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@Table(name = "item_recent_location")
public class ItemRecentLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer recentItemId;

    private String recentItemName;

    private Date recentItemStart;

    private Date recentItemEnd;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemInfor itemInfor;

    public ItemRecentLocation() {

    }
}
