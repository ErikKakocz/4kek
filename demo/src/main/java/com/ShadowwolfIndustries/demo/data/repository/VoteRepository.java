package com.ShadowwolfIndustries.demo.data.repository;

import com.ShadowwolfIndustries.demo.data.entity.VoteEntity;
import com.ShadowwolfIndustries.demo.projection.VoteProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Id;

public interface VoteRepository extends JpaRepository<VoteEntity, Long> {

    @Query("SELECT u from VoteEntity u where u.id = ?1")
    VoteProjection findprojectionById(Long id);

}
