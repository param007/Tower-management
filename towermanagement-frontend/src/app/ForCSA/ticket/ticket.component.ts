import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CSA } from 'src/app/model/csa';

import { TowerService } from 'src/app/service/tower.service';
import { Tower } from 'src/app/model/tower';
import { StatusService } from 'src/app/service/status.service';
import { Complaint } from '../../model/complaint';
import { TokenStorage } from '../../core/token.storage';

@Component({
  selector: 'app-ticket',
  templateUrl: './ticket.component.html',
  styleUrls: ['./ticket.component.css']
})
export class TicketComponent implements OnInit {
towerId:number;
tow:boolean=false;
complaint:Complaint;
fetchToken:string;
complaintCSA:CSA;
parsed2;
  constructor(private act:ActivatedRoute,
    private router:Router,
    private sSer:StatusService,private tSer:TowerService,private token:TokenStorage) {
     
     }

  ngOnInit() {
    this.complaint=new Complaint();
    this.complaint.tower=new Tower();
    // this.complaint.tower.csa=new CSA();
    this.complaintCSA=new CSA();

    this.complaint.csa=this.complaintCSA;
   // this.complaint=new Complaints();
    this.act.params.subscribe(
      (data)=>{
        this.complaint.csa.csaId=data['csaId'];
       
        
          this.complaint.tower.towerId=data['towerId'];
            this.tow=true;
        
        
      }
    );
  
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
 
  doComplaint(){

    this.complaint.dateOfApproval=null;
    // this.complaint.dateOfIssue=null;
    // alert(this.complaint.dateOfIssue);
    this.complaint.viewStatus=false;
    //this.complaint.actionStatus='NEW';
    // this.complaint.tower.csa.csaId=this.complaint.csa.csaId;
   // alert(this.complaint.tower.towerId);
    
    // this.tSer.getTowerById(this.complaint.tower.towerId).subscribe(
    // (data)=>{
     // this.complaint.tower.towerId;
      //alert( "TOwerId-->"+this.complaint.tower.towerId+" CsaId--->"+this.complaint.csa.csaId);
    // alert(this.complaint.tower.towerId);
    // },
    // (error)=>{alert("Error retrieving tower data");
    // console.log(error);}
    // )
    //this.complaint.csa=this.complaint.tower.csa;
    //alert(this.complaint.csa.csaId);

    this.sSer.addComplaint(this.complaint).subscribe(
      (data)=>  {this.router.navigateByUrl("/status")},
      (error)=>alert("Error adding data  "+error)
    );
  }

}
