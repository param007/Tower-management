import { Component, OnInit } from '@angular/core';
import { StatusService } from 'src/app/service/status.service';
import { Complaint } from 'src/app/model/complaint';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorage } from '../../core/token.storage';

@Component({
  selector: 'app-tower-complaint',
  templateUrl: './tower-complaint.component.html',
  styleUrls: ['./tower-complaint.component.css']
})
export class TowerComplaintComponent implements OnInit {
complaint:Complaint[];
towerId:number;
fetchToken:string;
parsed2;
gotComplaints:boolean=false;
  constructor(private sSer:StatusService,
    private act:ActivatedRoute, private router:Router,private token:TokenStorage) { }

  ngOnInit() {
    console.log(this.gotComplaints);
  this.act.params.subscribe(
    (data)=>this.towerId=data['towerId']
  )
  this.sSer.getComplaintsById(this.towerId).subscribe(
    (data)=>{this.complaint=data;
      this.gotComplaints=true;
  },
  (err)=>{
  this.gotComplaints=false;
}  );

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
