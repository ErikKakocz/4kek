package com.ShadowwolfIndustries.demo.service;

import com.ShadowwolfIndustries.demo.data.entity.UserEntity;
import com.ShadowwolfIndustries.demo.data.entity.VoteEntity;
import com.ShadowwolfIndustries.demo.data.repository.PostRepository;
import com.ShadowwolfIndustries.demo.data.repository.UserRepository;
import com.ShadowwolfIndustries.demo.data.repository.VoteRepository;
import com.ShadowwolfIndustries.demo.model.Exceptions.InvalidVoteException;
import com.ShadowwolfIndustries.demo.model.enums.VoteType;
import com.ShadowwolfIndustries.demo.projection.VoteProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    public VoteProjection find(Long id){
        return voteRepository.findprojectionById(id);
    }

    public Set<VoteProjection> findByPostId(Long postId)
    {
        return voteRepository.findprojectionsByPostId(postId);
    }

    public VoteProjection postVote(Long postId, Principal principal, VoteType type) throws NoSuchElementException, InvalidVoteException {
        UserEntity user=userRepository.findByUsername(principal.getName()).orElseThrow(() -> new NoSuchElementException());
        VoteEntity newVote=new VoteEntity();
        System.out.println("before FUBAR");
        Optional<VoteEntity> existingVote=voteRepository.findByPostAndUser(postId,(user.getId()));
        System.out.println("after FUBAR");
        if(existingVote.isPresent()){
            VoteEntity vote=existingVote.get();
            if(vote.getType()==type)
                throw new InvalidVoteException();
            else{
                voteRepository.delete(vote);
            }
        }
        newVote.setPost(postRepository.getOne(postId));
        newVote.setType(type);
        newVote.setVoter(userRepository.getOne(user.getId()));
        return (VoteProjection) voteRepository.save(newVote);
    }
}
