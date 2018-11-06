import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

@Injectable({
  providedIn: 'root'
})
export class ComplaintsService {
  baseUrl:string;
  constructor(private http:Http) { 
    this.baseUrl = "http://localhost:8080/complaints";
  }
}