package com.ShadowwolfIndustries.demo.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Set;
import java.util.List;

@Entity
@Data
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private long Id;
    private String username;
    private String password;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AuthorityEntity> authorities;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<PostEntity> posts;

}
