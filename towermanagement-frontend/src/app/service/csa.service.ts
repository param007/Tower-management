import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs';
import { CSA } from '../model/csa';
import { map } from 'rxjs/operators';
import { User } from '../model/user';


@Injectable({
  providedIn: 'root'
})
export class CsaService {
  baseUrl:string;
    constructor(private http:Http) { 
      this.baseUrl = "http://localhost:8080/towermgmt";
    }
  
    getBaseUrlById(id: number): string {
      return this.baseUrl + "/" + id;
    }
  
    
    getJsonContentTypeHeader(): RequestOptions {
      const headers = new Headers();
      headers.append('Content-Type', 'application/json');
      return new RequestOptions({ headers: headers });
    }
  
    getCsa():Observable<CSA>{ //getcsabyCsaid
      return this.http.get(this.baseUrl+"/csa").pipe(
        map(data => data.json())
      );
    }

    getCsaByUsername(username:string):Observable<User>{ //getCsabyUsername
      return this.http.get(this.baseUrl+"/username/"+username).pipe(
        map(data => data.json())
      );
    }
  }