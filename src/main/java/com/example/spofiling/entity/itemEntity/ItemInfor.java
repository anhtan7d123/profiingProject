package com.example.spofiling.entity.itemEntity;

import com.example.spofiling.entity.personEntity.PersonInfor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@Table(name = "item_information")
public class ItemInfor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer itemId;

    private String itemName;

    private String itemLabel;

    private String itemNumber;

    private String itemSize;

    private String itemColor;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "person_id")
    private PersonInfor itemOwner;

    @OneToMany(mappedBy = "itemInfor")
    private Collection<ItemRecentLocation> itemRecentLocations;

    public ItemInfor() {

    }
}
