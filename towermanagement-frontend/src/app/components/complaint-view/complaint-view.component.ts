import { Component, OnInit, Input } from '@angular/core';
import { ComplaintHistoryService } from 'src/app/service/complaint-history.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Complaint } from 'src/app/model/complaint';
import { CSA } from 'src/app/model/csa';
import { Tower } from 'src/app/model/tower';
import { TokenStorage } from '../../core/token.storage';

@Component({
  selector: 'app-complaint-view',
  templateUrl: './complaint-view.component.html',
  styleUrls: ['./complaint-view.component.css']
})
export class ComplaintViewComponent implements OnInit {

  complaint:Complaint;
 fetchToken:string;
 parsed2;
  @Input() dummy_id:number;
  constructor(private service:ComplaintHistoryService,private activatedRoute:ActivatedRoute,
    private token:TokenStorage,private router:Router) {
    this.complaint=new Complaint();
    this.complaint.csa=new CSA();
    this.complaint.tower=new Tower();
   }

  ngOnInit() {
    console.log(this.dummy_id);
    this.display(this.dummy_id);
    this.activatedRoute.params.subscribe(
      (params) => {
        let complaintId = params['id'];
        if (complaintId) {
          this.service.getComplaintById(complaintId).subscribe(
            (data) => this.complaint = data
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
display(dummy_id:number)
{
  this.service.getComplaintById(dummy_id).subscribe(
    (data) => this.complaint = data
  );
}

changeActionStatus(statusChanged:string,c:Complaint)
{
  c.actionStatus=statusChanged;
  this.service.updateComplaints(c).subscribe();
 // window.location.reload('/complaints');
}
}

 
  
