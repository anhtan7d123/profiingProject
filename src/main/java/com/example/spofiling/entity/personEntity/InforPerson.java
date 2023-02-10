package com.example.spofiling.entity.personEntity;

import com.example.spofiling.entity.itemEntity.ItemInfor;
import com.example.spofiling.entity.vehicleEntity.VehicleInfor;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "information_person")
public class InforPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer personId;

    private String personName;

    @OneToMany(mappedBy= "inforPerson")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Phone> phones;

    @OneToMany(mappedBy= "inforPerson")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Email> emails;

    @OneToMany(mappedBy = "inforPerson")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Image> images;

    @OneToMany(mappedBy = "inforPerson")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Location> locations;

    @OneToMany(mappedBy = "inforPerson")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<WorkExperience> workExperiences;

    @OneToMany(mappedBy = "vehicleOwner")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<VehicleInfor> vehicles;

    @OneToMany(mappedBy = "itemOwner")
    @LazyCollection(LazyCollectionOption.FALSE)
    private  List<ItemInfor> items;

    private Integer levelPopular;

    public InforPerson() {

    }

    public InforPerson(String personName, List<Phone> phones, Integer levelPopular) {
        this.personName = personName;
        this.phones = phones;
        this.levelPopular = levelPopular;
    }
}
