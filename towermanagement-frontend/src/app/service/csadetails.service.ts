import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { CSA } from '../model/csa';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class CSADetailsService {
  baseUrl: string;
  constructor(private http: Http) {
    this.baseUrl = "http://localhost:8080/towermgmt";
  }
  getAllCSAs(): Observable<CSA[]> {
    return this.http.get(this.baseUrl+"/getAllCsa").pipe(
      map(data => data.json())
    );
  }

  getCsaByCsaId(id:number):Observable<CSA>{
    return this.http.get(this.baseUrl+"/csa/"+id).pipe(
      map(data => data.json())
    );
  }
}
