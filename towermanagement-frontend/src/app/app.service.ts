import {Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { CSA } from  './model/csa';
import { Admin } from './model/admin';
import { Observable } from 'rxjs';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class UserService {

  constructor(private http: HttpClient) {}

  private userUrl = 'http://localhost:8080';

  public fetchAdminPage(): Observable<Admin[]> {
    return this.http.get<Admin[]>(this.userUrl + '/admin');
  }

  public fetchUserPage(id:number): Observable<CSA> {
    return this.http.get<CSA>(this.userUrl + '/user/'+id);
  }

}
