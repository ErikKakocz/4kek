package com.ShadowwolfIndustries.demo.data.entity;

import com.ShadowwolfIndustries.demo.model.enums.VoteType;
import lombok.Data;
import javax.persistence.*;


@Entity
@Data
public class VoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private VoteType type;

    @ManyToOne
    PostEntity post;

    @ManyToOne
    UserEntity voter;

}
