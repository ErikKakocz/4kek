import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { Authorization } from '../model/authorization-model';
import { AuthorizationService } from './authorization.service';
import { Observable } from 'rxjs';
import {tap} from 'rxjs/operators';
import { Injectable } from '@angular/core';


@Injectable()
export class AuthInterceptor implements HttpInterceptor{

    authorization: Authorization;

    constructor(private authorizationService: AuthorizationService){}

    intercept(req: HttpRequest<any>,next : HttpHandler) : Observable<HttpEvent<any>>{
        this.authorization = this.authorizationService.getLoggedInUser();
        if(this.authorization){
            req = req.clone({
                headers: req.headers.set("authorization", "basic "+ btoa(this.authorization.username+":"+this.authorization.password))
            });
        }

        return next.handle(req).pipe(tap(() => 
        { },
        err =>{
            if(err instanceof HttpErrorResponse){
                if(err.status == 401 ){
                    this.authorizationService.logout();
                }
            }

        } 
        ));

        
    }

}