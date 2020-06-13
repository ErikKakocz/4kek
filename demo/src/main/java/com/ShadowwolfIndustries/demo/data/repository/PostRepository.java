package com.ShadowwolfIndustries.demo.data.repository;

import com.ShadowwolfIndustries.demo.data.entity.PostEntity;
import com.ShadowwolfIndustries.demo.projection.PostProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    @Query("SELECT new com.ShadowwolfIndustries.demo.projection.PostProjection(p.id,p.title,p.pic,p.user.username,p.votes) from PostEntity p")
    List<PostProjection> findAllProjection();
}
