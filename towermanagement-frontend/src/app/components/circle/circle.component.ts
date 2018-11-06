import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Tower } from '../../model/tower';
import { CirclesService } from '../../service/circles.service';
import { TokenStorage } from '../../core/token.storage';
import { NavBarService } from '../../service/navbar.service';


@Component({
  selector: 'app-circle',
  templateUrl: './circle.component.html',
  styleUrls: ['./circle.component.css']
})
export class CircleComponent implements OnInit {

  towers:Tower[];
  logoUrl:string;
  mumbaiUrl:string;
  delhiUrl:string;
  hyderabadUrl:string;
  bangaloreUrl:string;
  jaipurUrl:string;
  parsed2;
  fetchToken:string;
  constructor( private activatedRoute: ActivatedRoute,
               private circleService: CirclesService,
               private router: Router,private token: TokenStorage,private nav:NavBarService
    ) {
      this.logoUrl="/assets/chennai01.png";
      this.mumbaiUrl="/assets/gateway.jpg";
      this.delhiUrl="/assets/qutub.jpg";
      this.hyderabadUrl="/assets/hyderabad01.png";
      this.bangaloreUrl="/assets/bangalore01.jpg";
      this.jaipurUrl="/assets/jaipur.png";
     }

  ngOnInit() {
    this.nav.show();
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
