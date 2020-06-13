package com.ShadowwolfIndustries.demo.rest;

import com.ShadowwolfIndustries.demo.data.entity.UserEntity;
import com.ShadowwolfIndustries.demo.projection.UserProjection;
import com.ShadowwolfIndustries.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserEntity> registerUser(@RequestBody UserEntity userEntity){
        return new ResponseEntity(userService.save(userEntity), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserProjection>> findAll(){
        return new ResponseEntity<>(userService.findAll(),HttpStatus.OK);
    }
}
