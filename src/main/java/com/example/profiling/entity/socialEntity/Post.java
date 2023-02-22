package com.example.profiling.entity.socialEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@Table(name = "post_list")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer postId;

    private String postName;

    private Long postTimestamp;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "account_id")
    private Account account;

    public Post() {

    }
}
