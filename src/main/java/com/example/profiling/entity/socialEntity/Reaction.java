package com.example.profiling.entity.socialEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@Table(name = "reaction_list")
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer reactionId;

    private String reactionName;

    private Long reactionTimestamp;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "account_id")
    private Account account;
}
