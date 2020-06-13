package com.ShadowwolfIndustries.demo.rest;

import com.ShadowwolfIndustries.demo.data.entity.PostEntity;
import com.ShadowwolfIndustries.demo.projection.PostProjection;
import com.ShadowwolfIndustries.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/add")
    public ResponseEntity<PostEntity> save(@ModelAttribute PostEntity postEntity, Principal principal){
        return new ResponseEntity<>(postService.save(postEntity,principal), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<PostProjection>> findAllProjection(){
        return new ResponseEntity<>(postService.findAllProjection(),HttpStatus.OK);
    }
    
}
