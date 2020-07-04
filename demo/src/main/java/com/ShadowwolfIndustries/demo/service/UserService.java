package com.ShadowwolfIndustries.demo.service;

import com.ShadowwolfIndustries.demo.data.entity.UserEntity;
import com.ShadowwolfIndustries.demo.data.repository.UserRepository;
import com.ShadowwolfIndustries.demo.model.Exceptions.InvalidLoginCredentialsException;
import com.ShadowwolfIndustries.demo.projection.UserProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity save(UserEntity userEntity){
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setAccountNonExpired(true);
        userEntity.setAccountNonLocked(true);
        userEntity.setCredentialsNonExpired(true);
        userEntity.setEnabled(true);
        return userRepository.save(userEntity);
    }

    public List<UserProjection> findAll(){
        return userRepository.findAllProjection();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    public UserProjection validateUserLogin(UserEntity entity) throws InvalidLoginCredentialsException {
        UserProjection projection=userRepository.findProjectionByUsername(entity.getUsername());
        String rawPass=entity.getPassword();
        String storedPass=userRepository.getPasswordById(projection.getId());
        if(passwordEncoder.matches(rawPass,storedPass))
            return projection;
        else
            throw new InvalidLoginCredentialsException();
    }
}
