import { Component, OnInit } from '@angular/core';
import { MatDialogConfig, MatDialog } from '@angular/material/dialog';
import { AddPostModalComponent } from '../add-post-modal/add-post-modal.component';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private dialog: MatDialog) { }

  clickCreatePost(){
    let config=new MatDialogConfig();
    config.minWidth=500;
    let dialogref=this.dialog.open(AddPostModalComponent,config);

    dialogref.afterClosed().subscribe( result => console.log("done"));
  }

  loginDialog(){
    console.log("logindialog!!!!");
  }

  ngOnInit(): void {
 
  }

}
