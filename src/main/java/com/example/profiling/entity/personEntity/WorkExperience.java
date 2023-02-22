package com.example.profiling.entity.personEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@Table(name = "work_experience")
public class WorkExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer workId;

    private String workCompany;

    private String workTitle;

    private Date workStart;

    private Date workEnd;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonInfor personInfor;

    public WorkExperience() {

    }
}
