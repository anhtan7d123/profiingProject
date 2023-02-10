package com.example.spofiling.entity.personEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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

    private String phoneDescribe;

    @ManyToOne(optional=false)
    @JoinColumn(name = "inforPerson_id")
    private InforPerson inforPerson;

    public Phone() {

    }
}
