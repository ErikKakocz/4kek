package com.ShadowwolfIndustries.demo.service;

import com.ShadowwolfIndustries.demo.data.entity.UserEntity;
import com.ShadowwolfIndustries.demo.data.entity.VoteEntity;
import com.ShadowwolfIndustries.demo.data.repository.UserRepository;
import com.ShadowwolfIndustries.demo.data.repository.VoteRepository;
import com.ShadowwolfIndustries.demo.model.Exceptions.InvalidVoteException;
import com.ShadowwolfIndustries.demo.model.enums.VoteType;
import com.ShadowwolfIndustries.demo.projection.VoteProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.HashSet;
import java.util.NoSuchElementException;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private UserRepository userRepository;

    public VoteEntity createVotes(){
        VoteEntity votes=new VoteEntity();
        votes.setDownvotes(0);
        votes.setUpvotes(0);
        votes.setUpvoters(new HashSet<UserEntity>());
        votes.setDownvoters(new HashSet<UserEntity>());
        return voteRepository.save(votes);
    }

    public VoteProjection find(long id){
        return voteRepository.findprojectionById(id);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE/*, propagation= Propagation.REQUIRED, readOnly=false*/)
    public VoteProjection updatePostUpvotes(long id, Principal principal, VoteType type) throws NoSuchElementException, InvalidVoteException {
        UserEntity user=userRepository.findByUsername(principal.getName()).orElseThrow(() -> new NoSuchElementException());
        VoteEntity voteEntity = voteRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        if(!(voteEntity.getUpvoters().contains(user))
                && type==VoteType.UPVOTE){
            voteEntity.setUpvotes(voteEntity.getUpvotes()+1);
            voteEntity.getUpvoters().add(user);
            if(voteEntity.getDownvoters().contains(user)){
                voteEntity.getDownvoters().remove(user);
                voteEntity.setDownvotes(voteEntity.getDownvotes()-1);
            }
        } else if(!(voteEntity.getDownvoters().contains(user))
                && type==VoteType.DOWNVOTE){
            voteEntity.setDownvotes(voteEntity.getDownvotes()+1);
            voteEntity.getDownvoters().add(user);
            if(voteEntity.getUpvoters().contains(user)){
                voteEntity.getUpvoters().remove(user);
                voteEntity.setUpvotes(voteEntity.getUpvotes()-1);
            }

        } else
            throw new InvalidVoteException();
        voteRepository.save(voteEntity);
        return voteRepository.findprojectionById(voteEntity.getId());
    }
}
