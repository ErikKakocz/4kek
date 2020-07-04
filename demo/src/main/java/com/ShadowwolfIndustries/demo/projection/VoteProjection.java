package com.ShadowwolfIndustries.demo.projection;

import com.ShadowwolfIndustries.demo.data.entity.UserEntity;
import com.ShadowwolfIndustries.demo.model.enums.VoteType;

import java.util.List;
import java.util.Set;


public interface VoteProjection {

    Long getId();
    VoteType getType();
    Long getVoter();
    Long getPost();

}
