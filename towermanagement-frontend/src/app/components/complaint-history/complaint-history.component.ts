import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { ComplaintHistoryService } from 'src/app/service/complaint-history.service';
import { Complaint } from 'src/app/model/complaint';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorage } from '../../core/token.storage';

@Component({
  selector: 'app-complaint-history',
  templateUrl: './complaint-history.component.html',
  styleUrls: ['./complaint-history.component.css']
})
export class ComplaintHistoryComponent implements OnInit {
complaintsList:Complaint[];
statusColors:string[];
fetchToken:string;
parsed2;
showDownload;
page:number;
  constructor(private service:ComplaintHistoryService,
    private activatedRoute:ActivatedRoute,
    private token:TokenStorage,private router:Router) {
      this.page=1;
    this.showDownload=false;
   }

  ngOnInit() {

    this.activatedRoute.params.subscribe(
      (params) => {
        let csaId = params['id'];
        let towerId = params['towerId'];
        
        if (csaId) {
          console.log("From CSA ID");
          this.service.getComplaintsByCsaId(csaId).subscribe(
            (data) => {this.complaintsList = data}
          );
        }else if (towerId) {
          console.log("From Tower ID");
         
          this.service.getComplaintsByTowerId(towerId).subscribe(
            (data) => {this.complaintsList = data,
            this.showDownload=true;}
          );}
        else
        {
          console.log("From All Complaints");
         
          this.service.getAllComplaints().subscribe(
            (data) => this.complaintsList=data
          );
        }
        
      }
    )

   
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
  get pageComplaints(){
    console.log("len"+this.complaintsList.length);
    return this.complaintsList.slice((this.page-1)*9,this.page*9);
    
 }
  parseJwt (token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    return JSON.parse(window.atob(base64));
};

}
