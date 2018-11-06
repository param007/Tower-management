import { Component, OnInit } from '@angular/core';

import { StatusService } from 'src/app/service/status.service';
import { Complaint } from '../../model/complaint';
import { Router } from '@angular/router';
import { TokenStorage } from '../../core/token.storage';
import { CsaService } from 'src/app/service/csa.service';
import { User } from 'src/app/model/user';

@Component({
  selector: 'app-status',
  templateUrl: './status.component.html',
  styleUrls: ['./status.component.css']
})
export class StatusComponent implements OnInit {
complaint:Complaint[];
fetchToken:string;
parsed2;
currentCSA:User;
  constructor(private sSer:StatusService,private csaServ:CsaService,
     private router:Router,private token:TokenStorage) { }

  ngOnInit() {

    
    this.fetchToken=this.token.getToken();
    
    if(this.fetchToken){
      console.log(this.fetchToken)
    this.parsed2=this.parseJwt(this.fetchToken);
      this.csaServ.getCsaByUsername(this.parsed2.sub).subscribe(
        (data)=>{
          this.currentCSA=data;
          this.sSer.getComplaints(this.currentCSA.id).subscribe(
            (data)=>this.complaint=data
          );
        },
        (error)=>{
          alert("CSA fetching failed");
        });
      
        
          if(this.parsed2.scopes=='ROLE_ADMIN'){
            this.router.navigateByUrl('/admin');
          console.log("ADDDDMIN");
          }
          else{
            
          console.log(this.parsed2.sub);
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
