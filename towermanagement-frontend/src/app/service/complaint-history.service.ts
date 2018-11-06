import { Injectable } from '@angular/core';
import { Http, Response,Headers,RequestOptions} from '@angular/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Complaint } from '../model/complaint';
@Injectable({
  providedIn: 'root'
})
export class ComplaintHistoryService {
  baseUrl: string;
  constructor(private http: Http) {
    this.baseUrl = "http://localhost:8080/towermgmt/Complaints";
  }
  getAllComplaints(): Observable<Complaint[]> {
    return this.http.get(this.baseUrl).pipe(
      map(data => data.json())
    );
  }
  getJsonContentTypeHeader(): RequestOptions {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return new RequestOptions({ headers: headers });
  }
  updateComplaints(c:Complaint):Observable<Response>{
    console.log(c.complaintId);
     return this.http.put(this.baseUrl+"/changeViewStatus",JSON.stringify(c), this.getJsonContentTypeHeader());
     }
  getComplaintById(id:number):Observable<Complaint>
  {
    return this.http.get(this.baseUrl+"/"+id).pipe(
      map(data=>data.json())
    )
  }
  getComplaintsByCsaId(id:number):Observable<Complaint[]>
  {
    console.log("hello");
    return this.http.get(this.baseUrl+"/csa/"+id).pipe(
      map(data=>data.json())
    )
  }

  getComplaintsByTowerId(towerid:number):Observable<Complaint[]>
  {
    console.log("hello");
    return this.http.get(this.baseUrl+"/tower/"+towerid).pipe(
      map(data=>data.json())
    )
  }
}
