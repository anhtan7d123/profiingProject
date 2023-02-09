package com.example.spofiling.entity.personEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@Table(name = "Image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer imageId;

    private String imageName;

    private String imageType;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private InforPerson inforPerson;
}