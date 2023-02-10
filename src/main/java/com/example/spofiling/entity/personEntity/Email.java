package com.example.spofiling.entity.personEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@Table(name = "email_list")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer emailId;

    private String emailDetail;

    @ManyToOne(optional=false)
    @JoinColumn(name = "person_id")
    private InforPerson inforPerson;

    public Email() {

    }
}
