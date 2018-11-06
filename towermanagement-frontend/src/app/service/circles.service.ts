import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers, Response } from '@angular/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Tower } from '../model/tower';

@Injectable({
  providedIn: 'root'
})
export class CirclesService {

  baseUrl: string;
  constructor(private http: Http) {
    this.baseUrl = "http://localhost:8080/towermgmt"
  }
  getBaseUrlByCircle(circle: string): string {
    return this.baseUrl + "/Circlee/" + circle;

  }
  getJsonContentTypeHeader(): RequestOptions {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return new RequestOptions({ headers: headers });
  }

getTowerByCircle(circle:string): Observable<Tower[]> {
    return this.http.get(this.getBaseUrlByCircle(circle)).pipe(
      map(data => data.json())
    );
  }
}
