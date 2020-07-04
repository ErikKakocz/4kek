import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { MatToolbarModule } from '@angular/material/toolbar';
import {MatDialogModule} from '@angular/material/dialog';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PostComponent } from './post/post.component';
import { from } from 'rxjs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavbarComponent } from './navbar/navbar.component';
import { AddPostModalComponent } from './add-post-modal/add-post-modal.component';
import { VoteBarComponent } from './vote-bar/vote-bar.component';
import { LoginModalComponent } from './login-modal/login-modal.component';
import { LoggedInMenuComponent } from './logged-in-menu/logged-in-menu.component';

@NgModule({
  declarations: [
    AppComponent,
    PostComponent,
    NavbarComponent,
    AddPostModalComponent,
    VoteBarComponent,
    LoginModalComponent,
    LoggedInMenuComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatDialogModule,
    MatInputModule,
    MatFormFieldModule
  ],
  entryComponents: [
    AddPostModalComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
