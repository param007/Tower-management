import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorage } from '../core/token.storage';


@Injectable({
  providedIn: 'root'
})
export class NavBarService {
  visible: boolean;
  fetchToken:string;
  parsed2;
  userAdmin;
  Csa;
  constructor(private router:Router,private token:TokenStorage) { this.visible = false; 
  this.checkLoggedInUser();}

  hide() { this.visible = false; }

  show() { this.visible = true; }

  toggle() { this.visible = !this.visible; }

  checkLoggedInUser(){
    this.fetchToken=this.token.getToken();
    if(this.fetchToken){
    this.parsed2=this.parseJwt(this.fetchToken);
 
 
          if(this.parsed2.scopes=='ROLE_ADMIN'){
            this.userAdmin=true;
            this.Csa=false;
          console.log("ADDDDMIN");
          }
          else{
            this.userAdmin=false;
            this.Csa=true;
          console.log("USEEEER");
          }
        }
  }
  parseJwt (token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    return JSON.parse(window.atob(base64));
};

}