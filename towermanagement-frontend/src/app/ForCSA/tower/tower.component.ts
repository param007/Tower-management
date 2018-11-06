import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TowerService } from 'src/app/service/tower.service';
import { Tower } from 'src/app/model/tower';
import { TokenStorage } from '../../core/token.storage';

@Component({
  selector: 'app-tower',
  templateUrl: './tower.component.html',
  styleUrls: ['./tower.component.css']
})
export class TowerComponent implements OnInit {
towerId:number;
tower:Tower;
fetchToken:string;
parsed2;
  constructor(private act:ActivatedRoute,
    private tSer:TowerService, private router:Router,private token:TokenStorage) { }

  ngOnInit() {
    this.act.params.subscribe(
      (data)=>{this.towerId=data['towerId'];

      }
    );
    this.tSer.getTowerById(this.towerId).subscribe(
      (data)=>this.tower=data
    )
    this.fetchToken=this.token.getToken();
    if(this.fetchToken){
    this.parsed2=this.parseJwt(this.fetchToken);
 
 
          if(this.parsed2.scopes=='ROLE_ADMIN'){
            this.router.navigateByUrl('/admin');
          console.log("ADDDDMIN");
          }
          else{
            // 
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
