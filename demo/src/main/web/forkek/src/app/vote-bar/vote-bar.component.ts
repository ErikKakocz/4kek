import { Component, OnInit, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { votes } from '../model/votes_model';
@Component({
  selector: 'app-vote-bar',
  templateUrl: './vote-bar.component.html',
  styleUrls: ['./vote-bar.component.css']
})
export class VoteBarComponent implements OnInit {

  @Input()
  votesId: number;
  vote: votes;

  constructor(private httpClient: HttpClient) { }

  ngOnInit(): void {
    this.vote=new votes(0,0,[],[]);
    console.log("app-vote-bar initialization votes-id:"+this.votesId);
    this.updateVotes();
    this.updateVoteNumbers();
  }

  public updateVotes(): void{
    this.httpClient.get<votes>("http://localhost:8081/api/votes/find?id="+this.votesId).subscribe(ob => {
      this.vote=ob;
      this.updateVoteNumbers();
    })
  }

  updateVoteNumbers(): void{
    document.getElementById("likeButton").innerHTML="I lik dat ("+this.vote.upvotes+")";
    document.getElementById("dislikeButton").innerHTML="meh... ("+this.vote.downvotes+")";
  }


}
