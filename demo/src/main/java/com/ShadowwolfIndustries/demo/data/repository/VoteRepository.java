package com.ShadowwolfIndustries.demo.data.repository;

import com.ShadowwolfIndustries.demo.data.entity.VoteEntity;
import com.ShadowwolfIndustries.demo.projection.VoteProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface VoteRepository extends JpaRepository<VoteEntity, Long> {

    @Query("SELECT v from VoteEntity v WHERE v.id = :id")
    VoteProjection findprojectionById(Long id);


}
