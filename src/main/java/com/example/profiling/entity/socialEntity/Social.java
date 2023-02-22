package com.example.profiling.entity.socialEntity;

import com.example.profiling.entity.personEntity.PersonInfor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Table(name = "social_list")
public class Social {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer socialId;

    private String socialName;

    @OneToMany(mappedBy = "social")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Account> accounts;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "person_id")
    private PersonInfor personInfor;
    public Social() {

    }
}
