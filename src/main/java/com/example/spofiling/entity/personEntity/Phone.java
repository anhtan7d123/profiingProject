package com.example.spofiling.entity.personEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@Table(name = "phone_list")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer phoneId;

    private String phoneNumber;

    private String phoneDescribe;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private InforPerson inforPerson;

    public Phone() {

    }
}
