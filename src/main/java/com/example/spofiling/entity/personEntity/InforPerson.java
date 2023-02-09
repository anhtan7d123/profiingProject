package com.example.spofiling.entity.personEntity;

import com.example.spofiling.entity.itemEntity.ItemInfor;
import com.example.spofiling.entity.vehicleEntity.VehicleInfor;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@Table(name = "information_person")
public class InforPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer personId;

    private String personName;

    @OneToMany(mappedBy = "inforPerson")
    private Collection<Phone> phones;

    @OneToMany(mappedBy = "inforPerson")
    private Collection<Email> emails;

    @OneToMany(mappedBy = "inforPerson")
    private Collection<Image> images;

    @OneToMany(mappedBy = "inforPerson")
    private Collection<Location> locations;

    @OneToMany(mappedBy = "inforPerson")
    private Collection<WorkExperience> workExperiences;

    @ManyToMany
    @JoinTable(name = "person_vehicle",
            joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
    private  Collection<VehicleInfor> vehicles;

    @ManyToMany
    @JoinTable(name = "person_item",
            joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
    private  Collection<ItemInfor> items;

    private Integer levelPopular;

    public InforPerson() {

    }
}
