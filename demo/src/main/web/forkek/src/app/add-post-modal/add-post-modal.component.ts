import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-add-post-modal',
  templateUrl: './add-post-modal.component.html',
  styleUrls: ['./add-post-modal.component.css']
})
export class AddPostModalComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<AddPostModalComponent>) { }

  ngOnInit(): void {
  }

  submitPost(): void{
    console.log("this will submit the post!");
    this.dialogRef.close();
  }

  closeThis(): void{
    this.dialogRef.close();
  }


}
