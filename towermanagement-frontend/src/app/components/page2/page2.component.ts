import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Tower } from '../../model/tower';
import { CirclesService } from '../../service/circles.service';
import { TokenStorage } from '../../core/token.storage';


@Component({
  selector: 'app-page2',
  templateUrl: './page2.component.html',
  styleUrls: ['./page2.component.css']
})
export class Page2Component implements OnInit {

  towers:Tower[];
  jaipurUrl:string;
  maduraiUrl:string;
  agraUrl:string;
  mysoreUrl:string;
  kochiUrl:string;
  fetchToken:string;
  parsed2;
  constructor( private activatedRoute: ActivatedRoute,
    private circleService: CirclesService,private router:Router,private token:TokenStorage
) {
  this.jaipurUrl="/assets/jaipur.png";
  this.maduraiUrl="/assets/meenakshi.jpg";
  this.agraUrl="/assets/taj01.png";
  this.mysoreUrl="/assets/mysore01.jpg";
  this.kochiUrl="/assets/kochin.jpg";
}

  ngOnInit() {
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