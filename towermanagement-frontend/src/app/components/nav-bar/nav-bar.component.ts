import { Component, OnInit, NgZone } from '@angular/core';
import { Complaint } from 'src/app/model/complaint';
import { ComplaintHistoryService } from 'src/app/service/complaint-history.service';
import { TokenStorage } from '../../core/token.storage';
import { Router } from '@angular/router';
import { NavBarService } from '../../service/navbar.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  complaintsList:Complaint[];
  count:number=0;
  navbarOpen = false;
  notifyClicked=false;
  showedDropdown=false;
  constructor(private service:ComplaintHistoryService
    ,private token: TokenStorage
    ,private router:Router,public nav: NavBarService,private ngZone:NgZone)
  {

  }
  ngOnInit()
  {
  this.service.getAllComplaints().subscribe(
    (data) => {this.complaintsList=data, this.funNumber(this.complaintsList)}
  );
 
  }
  funNumber(complaints:Complaint[])
  {
   for(let c of complaints)
   {
       if(c.viewStatus==false)
       {
         this.count+=1;
       }
   }
   console.log(this.count);
  }
  toggleNavbar() {
    this.navbarOpen = !this.navbarOpen;
  }

  hideNotify()
  {
    console.log("Hello");
    document.getElementById('notify-span').style.display = "none";
    this.notifyClicked=true;
    this.updateData();
  }
  updateData()
  {
    if(this.notifyClicked==true)
    {
    console.log("xxxxxx");
    for(let c of this.complaintsList)
    {
        if(c.viewStatus==false)
        {
           c.viewStatus=true;
           console.log("hello in update data");
           this.service.updateComplaints(c).subscribe(

           );
            
         }
    }
  }
    
  }

  signOff(){
    this.token.signOut();
    console.log("Logged out");
    //this.router.navigateByUrl("/");
    window.location.replace('/');
}

}
