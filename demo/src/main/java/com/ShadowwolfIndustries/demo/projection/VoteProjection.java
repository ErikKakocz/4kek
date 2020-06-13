package com.ShadowwolfIndustries.demo.projection;

import java.util.HashSet;

public interface VoteProjection {

    Long getId();
    int getUpvotes();
    int getDownvotes();
    HashSet<UserProjection> getUpvoters();
    HashSet<UserProjection> getDownvoters();

}
