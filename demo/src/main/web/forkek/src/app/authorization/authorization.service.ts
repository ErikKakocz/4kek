import { Injectable } from '@angular/core';
import { Authorization } from '../model/authorization-model';

@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {

  constructor() { }

  login(authorization : Authorization){
    sessionStorage.removeItem('authorization');
    sessionStorage.setItem('authorization',JSON.stringify(authorization));
  }

  logout(){
    sessionStorage.removeItem('authorization');
  }

  getLoggedInUser() : Authorization {
    return JSON.parse(sessionStorage.getItem('authorization'));
  }

  isLoggedIn(): Boolean{
    return this.getLoggedInUser() ? true : false;
  }
}
