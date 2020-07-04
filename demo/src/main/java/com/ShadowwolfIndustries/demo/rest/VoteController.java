package com.ShadowwolfIndustries.demo.rest;


import com.ShadowwolfIndustries.demo.model.Exceptions.InvalidVoteException;
import com.ShadowwolfIndustries.demo.model.enums.VoteType;
import com.ShadowwolfIndustries.demo.projection.VoteProjection;
import com.ShadowwolfIndustries.demo.service.VoteService;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @CrossOrigin
    @GetMapping("/find")
    public ResponseEntity<Set<VoteProjection>> findVoteProjectionsByPostId(@RequestParam Long postId){
        return new ResponseEntity<>(voteService.findByPostId(postId),HttpStatus.OK);
    }

    @PostMapping("/upvote")
    public ResponseEntity<VoteProjection> upvotePost(@RequestBody Long postId, Principal principal){
        try {
            return new ResponseEntity<>(voteService.postVote(postId,principal,VoteType.UPVOTE), HttpStatus.OK);
        } catch (InvalidVoteException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/downvote")
    public ResponseEntity<VoteProjection> downvotePost(@RequestBody Long postId, Principal principal){
        try {
            return new ResponseEntity<>(voteService.postVote(postId,principal,VoteType.DOWNVOTE), HttpStatus.OK);
        } catch (InvalidVoteException e) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }
}
