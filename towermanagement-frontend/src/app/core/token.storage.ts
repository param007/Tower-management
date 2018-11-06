import { Injectable } from '@angular/core';


const TOKEN_KEY = 'AuthToken';

@Injectable()
export class TokenStorage {
  isLoggedIn=false;
  constructor() { }

  signOut() {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.clear();
  }

  public saveToken(token: string) {
    console.log("Saved");
    this.isLoggedIn=true;
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY,  token);
    console.log("Pakka Saved");
  }

  public getToken(): string {
    return sessionStorage.getItem(TOKEN_KEY);
  }
}
