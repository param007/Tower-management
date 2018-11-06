import { Component, OnInit } from '@angular/core';
import { Tower } from 'src/app/model/tower';
import { TowerService } from 'src/app/service/tower.service';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorage } from '../../core/token.storage';
@Component({
  selector: 'app-tower-details',
  templateUrl: './tower-details.component.html',
  styleUrls: ['./tower-details.component.css']
})
export class TowerDetailsComponent implements OnInit {
  towers:Tower[];
  fetchToken:string;
  parsed2;
  constructor(private service:TowerService,private activatedRoute:ActivatedRoute,private router:Router,private token:TokenStorage) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe(
      (params) => {
        let csaId = params['id'];
        if (csaId) {
          this.service.getTowersByCsa(csaId).subscribe(
            (data) => this.towers = data
          );
        }
      }
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
