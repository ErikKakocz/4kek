package com.ShadowwolfIndustries.demo.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class VoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int upvotes;
    private int downvotes;

    @OneToMany
    @JsonIgnore
    private Set<UserEntity> upvoters;

    @OneToMany
    @JsonIgnore
    private Set<UserEntity> downvoters;
}
