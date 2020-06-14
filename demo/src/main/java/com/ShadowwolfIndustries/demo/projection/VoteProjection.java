package com.ShadowwolfIndustries.demo.projection;

import com.ShadowwolfIndustries.demo.data.entity.UserEntity;

import java.util.List;
import java.util.Set;


public interface VoteProjection {

    Long getId();
    int getUpvotes();
    int getDownvotes();
    List<UserProjection> upvoters();
    List<UserProjection> downvoters();

}
