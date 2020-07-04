package com.ShadowwolfIndustries.demo.service;

import com.ShadowwolfIndustries.demo.data.entity.PostEntity;
import com.ShadowwolfIndustries.demo.data.entity.UserEntity;
import com.ShadowwolfIndustries.demo.data.entity.VoteEntity;
import com.ShadowwolfIndustries.demo.data.repository.PostRepository;
import com.ShadowwolfIndustries.demo.data.repository.UserRepository;
import com.ShadowwolfIndustries.demo.data.repository.VoteRepository;
import com.ShadowwolfIndustries.demo.projection.PostProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private VoteService voteService;

    @Autowired
    private UserRepository userRepository;

    public PostEntity save(PostEntity post, Principal principal){
        UserEntity user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        post.setUser(user);
        return postRepository.save(post);
    }

    public List<PostProjection> findAllProjection(){
        return postRepository.findAllProjection();
    }
}
