import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Post } from '../model/post-model';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {
  posts: any;
  constructor(private httpClient: HttpClient) { }

  ngOnInit(): void {
    console.log("post initialization:")
    this.httpClient.get<Post[]>("http://localhost:8081/api/posts/all").subscribe(posts => {
      this.posts = posts;
      console.log(posts);
    });
  }

}
