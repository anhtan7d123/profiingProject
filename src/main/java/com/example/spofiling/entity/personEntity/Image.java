package com.example.spofiling.entity.personEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @JoinColumn(name = "person_id")
    private InforPerson inforPerson;

    public Image() {

    }
}
