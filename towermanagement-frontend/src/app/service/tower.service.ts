import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { CSA } from '../model/csa';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Tower } from '../model/tower';
@Injectable({
  providedIn: 'root'
})
export class TowerService {
  baseUrl: string;
  constructor(private http: Http) {
    this.baseUrl = "http://localhost:8080/towermgmt/Towers";
  }
  getTowersByCsa(csaId:number):Observable<Tower[]>
  {
    return this.http.get(this.baseUrl+"/csa/"+csaId).pipe(
      map(data => data.json())
    );
  }

  getBaseUrlById(id: number): string {
    return this.baseUrl + "/" + id;

  }
  
  getBaseUrlByCsaId(id: number): string {
    return this.baseUrl + "/csa/" + id;

  }

  getTowerById(id: number): Observable<Tower> {
    return this.http.get(this.getBaseUrlById(id)).pipe(
      map(data => data.json())
    );
  }
  getTowers():Observable<Tower[]>{    //getalltowers
    return this.http.get(this.baseUrl).pipe(
      map(data => data.json())
    );
  }
  getTowerByCsaId(id: number): Observable<Tower[]> {
    return this.http.get(this.getBaseUrlByCsaId(id)).pipe(
      map(data => data.json())
    );
  }
}
