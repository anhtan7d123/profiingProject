package com.example.spofiling.entity.personEntity;

import com.example.spofiling.entity.itemEntity.ItemInfor;
import com.example.spofiling.entity.vehicleEntity.VehicleInfor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "information_person")
public class PersonInfor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer personId;

    private String personName;

    @OneToMany(mappedBy= "personInfor")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Phone> phones;

    @OneToMany(mappedBy= "personInfor")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Email> emails;

    @OneToMany(mappedBy = "personInfor")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Image> images;

    @OneToMany(mappedBy = "personInfor")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Location> locations;

    @OneToMany(mappedBy = "personInfor")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<WorkExperience> workExperiences;

//    @JoinTable(name = "Person_Vehicle",
//    joinColumns = @JoinColumn(name = "person_id", referencedColumnName = "PID"),
//    inverseJoinColumns = @JoinColumn)
    @OneToMany(mappedBy = "vehicleOwner")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<VehicleInfor> vehicleInfors;

    @OneToMany(mappedBy = "itemOwner")
    @LazyCollection(LazyCollectionOption.FALSE)
    private  List<ItemInfor> itemInfors;

    private Integer levelPopular;

    public PersonInfor() {

    }

    public PersonInfor(String personName, List<Phone> phones, Integer levelPopular) {
        this.personName = personName;
        this.phones = phones;
        this.levelPopular = levelPopular;
    }
}
