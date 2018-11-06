import { Injectable } from '@angular/core';
import { Http, Headers,RequestOptions } from '@angular/http';

import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Complaint } from '../model/complaint';


@Injectable({
  providedIn: 'root'
})
export class StatusService {
  baseUrl:string;
  constructor(private http:Http) { 
    this.baseUrl = "http://localhost:8080/towermgmt";
  }
  getBaseUrlById(id: number): string {
    return this.baseUrl + "/Complaints/tower/" + id;
  }
  getComplaints(id:number):Observable<Complaint[]>{
    return this.http.get(this.baseUrl+"/Complaints/csa/"+id).pipe(
      map(data => data.json())
    );
  }
  getComplaintsById(towerId:number):Observable<Complaint[]>{ //getComplaints bytower id
    return this.http.get(this.getBaseUrlById(towerId)).pipe(
      map(data => data.json()));
   
  }
  getJsonContentTypeHeader(): RequestOptions {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return new RequestOptions({ headers: headers });
  }
  addComplaint(complaints:Complaint):Observable<Complaint>{ 
    return this.http.post(this.baseUrl+"/addComplaint", JSON.stringify(complaints), this.getJsonContentTypeHeader()).pipe(
      map(data => data.json())

    );
  }

}
