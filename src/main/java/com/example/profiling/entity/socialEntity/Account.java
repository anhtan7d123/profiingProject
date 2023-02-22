package com.example.profiling.entity.socialEntity;

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
@Table(name = "account_list")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer accountId;

    private String accountName;

    private String accountRate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "social_id")
    private Social social;

    @OneToMany(mappedBy = "account")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Post> posts;

    @OneToMany(mappedBy = "account")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Comment> comments;

    @OneToMany(mappedBy = "account")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Reaction> reactions;

    @OneToMany(mappedBy = "account")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<RelationshipObject> relationshipObjects;

    public Account() {

    }
}
