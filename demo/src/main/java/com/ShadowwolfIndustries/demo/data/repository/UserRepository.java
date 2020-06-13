package com.ShadowwolfIndustries.demo.data.repository;

import com.ShadowwolfIndustries.demo.data.entity.UserEntity;
import com.ShadowwolfIndustries.demo.projection.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    @Query("SELECT u from UserEntity u")
    List<UserProjection> findAllProjection();
}
