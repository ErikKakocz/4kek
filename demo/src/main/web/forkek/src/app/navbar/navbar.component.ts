import { Component, OnInit } from '@angular/core';
import { MatDialogConfig, MatDialog } from '@angular/material/dialog';
import { AddPostModalComponent } from '../add-post-modal/add-post-modal.component';
import { LoginModalComponent} from '../login-modal/login-modal.component';
import { AuthorizationService } from '../authorization/authorization.service';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  username:String

  constructor(
    private dialog: MatDialog,
    private authorizationService : AuthorizationService
    ) { }

  clickCreatePost(){
    let config=new MatDialogConfig();
    config.minWidth=500;
    let dialogref=this.dialog.open(AddPostModalComponent,config);

    dialogref.afterClosed().subscribe( result => console.log(result));
  }

  loginDialog(){
    let config=new MatDialogConfig();
    config.minWidth=500;
    let dialogref=this.dialog.open(LoginModalComponent,config).afterClosed().subscribe(response => {
      this.updateUserName()
    });
  }

  ngOnInit(): void {
    this.updateUserName()  
  }

  updateUserName(){
    if(this.authorizationService.isLoggedIn())
      this.username=this.authorizationService.getLoggedInUser().username;
    else
      this.username="Y u no log in?";    
  }

}
