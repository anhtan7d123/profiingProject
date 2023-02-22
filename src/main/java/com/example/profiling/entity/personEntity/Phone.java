package com.example.profiling.entity.personEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "phone_list")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer phoneId;

    private String phoneNumber;

    private String phoneNote;

    private String phoneType;

    private Integer phoneRate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "inforPerson_id")
    private PersonInfor personInfor;

    public Phone() {

    }
}
