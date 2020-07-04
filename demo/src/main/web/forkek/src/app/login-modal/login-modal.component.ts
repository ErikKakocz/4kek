import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MatDialogRef, MatDialog } from '@angular/material/dialog';
import { User } from '../model/user-model';
import { MatInput } from '@angular/material/input';
import { HttpHeaders } from '@angular/common/http'; 
import { Authorization } from '../model/authorization-model';
import { Router } from '@angular/router';
import { AuthorizationService } from '../authorization/authorization.service';

@Component({
  selector: 'app-login-modal',
  templateUrl: './login-modal.component.html',
  styleUrls: ['./login-modal.component.css']
})
export class LoginModalComponent implements OnInit {
  user: Authorization = new Authorization();
  
  constructor(
    private dialogRef: MatDialogRef<LoginModalComponent>,
    private httpClient: HttpClient,
    private router: Router,
    private authorizationService : AuthorizationService) 
    {  }

  ngOnInit(): void {
    
  }

  submitLogin(): void{
    this.authorizationService.login(this.user);
    let url="http://localhost:8081/api/users/login";
    this.httpClient.get(url).subscribe( response => {
      this.authorizationService.login(this.user);
      this.closeThis();
    },
    error => console.log("Bad Credentials."));
  }

  registerUser(): void{

  }

  closeThis(): void{
    this.dialogRef.close();
  }

}
