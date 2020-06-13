package com.ShadowwolfIndustries.demo.rest;

import com.ShadowwolfIndustries.demo.data.entity.VoteEntity;
import com.ShadowwolfIndustries.demo.model.Exceptions.InvalidVoteException;
import com.ShadowwolfIndustries.demo.model.enums.VoteType;
import com.ShadowwolfIndustries.demo.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping("/upvote")
    public ResponseEntity<VoteEntity> upvotePost(@RequestBody VoteEntity vote, Principal principal){
        try {
            return new ResponseEntity<>(voteService.updatePostUpvotes(vote, principal, VoteType.UPVOTE), HttpStatus.OK);
        } catch (InvalidVoteException e) {
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/downvote")
    public ResponseEntity<VoteEntity> downvotePost(@RequestBody VoteEntity vote, Principal principal){
        try {
            return new ResponseEntity<>(voteService.updatePostUpvotes(vote, principal, VoteType.DOWNVOTE), HttpStatus.OK);
        } catch (InvalidVoteException e) {
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
    }
}
