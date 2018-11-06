import { Component, OnInit } from '@angular/core';
import { CSADetailsService } from 'src/app/service/csadetails.service';
import { CSA } from 'src/app/model/csa';
import { Router } from '@angular/router';
import { TokenStorage } from '../../core/token.storage';

@Component({
  selector: 'app-csadetails',
  templateUrl: './csadetails.component.html',
  styleUrls: ['./csadetails.component.css']
})
export class CSADetailsComponent implements OnInit {
csaList:CSA[];
  constructor(private service:CSADetailsService,private router:Router,private token:TokenStorage) { }
  fetchToken:string;
  parsed2;
  ngOnInit() {
    this.service.getAllCSAs().subscribe(
      (data) => this.csaList=data
    );
    this.fetchToken=this.token.getToken();
    if(this.fetchToken){
    this.parsed2=this.parseJwt(this.fetchToken);
 
 
          if(this.parsed2.scopes=='ROLE_ADMIN'){
          console.log("ADDDDMIN");
          }
          else{
            this.router.navigateByUrl('/csa'); 
          console.log("USEEEER");
          }
        }else{
          this.router.navigateByUrl("/");
        }
  }
  parseJwt (token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    return JSON.parse(window.atob(base64));
};

}
