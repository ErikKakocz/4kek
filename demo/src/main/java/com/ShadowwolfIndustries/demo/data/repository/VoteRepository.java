package com.ShadowwolfIndustries.demo.data.repository;

import com.ShadowwolfIndustries.demo.data.entity.VoteEntity;
import com.ShadowwolfIndustries.demo.model.enums.VoteType;
import com.ShadowwolfIndustries.demo.projection.VoteProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;
import java.util.Optional;


public interface VoteRepository extends JpaRepository<VoteEntity, Long> {

    @Query("SELECT v from VoteEntity v WHERE v.id = :id")
    VoteProjection findprojectionById(Long id);

    @Query("SELECT v from VoteEntity v WHERE v.post.id = :id")
    Set<VoteProjection> findprojectionsByPostId(Long id);

    @Query("SELECT v from VoteEntity v WHERE v.post.id = :postId AND v.voter.id = :userId")
    Optional<VoteEntity> findByPostAndUser(Long postId, Long userId);

}
