import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';

import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Admin } from '../model/admin';
import { User } from '../model/user';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  baseUrl: string;
  otp: string;
  admins;
  constructor(private http: Http, private httpClient: HttpClient) {
    this.baseUrl = "http://localhost:8080";
  }

  getJsonContentTypeHeader(): RequestOptions {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return new RequestOptions({ headers: headers });
  }

  getJsonContentTypeHeaderForText(): RequestOptions {
    const headers = new Headers();
    headers.append('Content-Type', 'application/text');
    return new RequestOptions({ headers: headers });
  }


  resetPassword(mail: String): Observable<any> {
    let otp = (((Math.random() * 1000000).toFixed(0)).toString());
    this.otp = otp;
    //console.log(this.otp);
    return this.http.post(this.baseUrl + "/towermgmt/reset/" + mail, JSON.stringify(otp),
      this.getJsonContentTypeHeader());
        
  }
  checkOtp(enteredOtp: string): boolean {
    return (this.otp == enteredOtp ? true : false);
  }

  changePassword(username: string, password: string): Observable<User> {
    console.log("inside password change..before request");
    return this.http.post(this.baseUrl + "/towermgmt/changepwd/" + username, password,
      this.getJsonContentTypeHeaderForText()).pipe(
        map(data => data.json())
      );
  }

  getAllAdmins(): Observable<any> {
    return this.httpClient.get(this.baseUrl + "/secure/admin");
    // .subscribe(
    //    (data)=>{
    //      this.admins=data;
    //      console.log(this.admins);
    //       }   );
    // return this.admins;
  }
}
