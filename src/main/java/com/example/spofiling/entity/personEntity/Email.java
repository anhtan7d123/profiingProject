package com.example.spofiling.entity.personEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

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

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "person_id")
    private PersonInfor personInfor;

    public Email() {

    }
}
