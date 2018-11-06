import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Tower } from 'src/app/model/tower';
import { TowerService } from '../../service/tower.service';
import { TokenStorage } from '../../core/token.storage';

@Component({
  selector: 'app-view-tower',
  templateUrl: './view-tower.component.html',
  styleUrls: ['./view-tower.component.css']
})
export class ViewTowerComponent implements OnInit {
  tower:Tower;
  fetchToken:string;
  parsed2;
  constructor(private towerService: TowerService,
    private activatedRoute: ActivatedRoute,private router:Router,private token:TokenStorage) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe(
      (params) => {
        let towerId = params['id'];
        if (towerId) {
          this.towerService.getTowerById(towerId).subscribe(
            (data) => this.tower = data
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
