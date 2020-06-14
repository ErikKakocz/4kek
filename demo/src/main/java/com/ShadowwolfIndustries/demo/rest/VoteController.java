package com.ShadowwolfIndustries.demo.rest;

import com.ShadowwolfIndustries.demo.data.entity.VoteEntity;
import com.ShadowwolfIndustries.demo.model.Exceptions.InvalidVoteException;
import com.ShadowwolfIndustries.demo.model.enums.VoteType;
import com.ShadowwolfIndustries.demo.projection.VoteProjection;
import com.ShadowwolfIndustries.demo.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @GetMapping("/find")
    public ResponseEntity<VoteProjection> findVoteProjectionById(@RequestBody long id){
        return new ResponseEntity<>(voteService.find(id),HttpStatus.OK);
    }

    @PostMapping("/upvote")
    public ResponseEntity<VoteProjection> upvotePost(@RequestBody long id, Principal principal){
        try {
            return new ResponseEntity<>(voteService.updatePostUpvotes(id, principal, VoteType.UPVOTE), HttpStatus.OK);
        } catch (InvalidVoteException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/downvote")
    public ResponseEntity<VoteProjection> downvotePost(@RequestBody long id, Principal principal){
        try {
            return new ResponseEntity<>(voteService.updatePostUpvotes(id, principal, VoteType.DOWNVOTE), HttpStatus.OK);
        } catch (InvalidVoteException e) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }
}
