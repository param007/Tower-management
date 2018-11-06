import { Component, OnInit } from '@angular/core';
import { AdminService } from '../service/admin.service';
import { Router } from '@angular/router';
import { Admin } from '../model/admin';

@Component({
  selector: 'app-forgot-pass',
  templateUrl: './forgot-pass.component.html',
  styleUrls: ['./forgot-pass.component.css']
})
export class ForgotPassComponent implements OnInit {
//admin:Admin;
//otp:string;
checkInt:number;
  constructor(private adminService:AdminService,private router:Router) { }
email:string;
  ngOnInit() {
  // console.log(this.otp=((Math.random()*1000000).toFixed(0)).toString());
  }

  resetPassword(){
    this.adminService.resetPassword(this.email).subscribe(
      (resp)=>{
        if(resp.ok){
        console.log("mail Successully sent")
        this.router.navigateByUrl('/setNewPassword');
        }else{
          console.log(    "POOOOOOp"   )
        }
      }
      ,
      (err)=>{console.log("Email Id not macthed")}
      
  
    );
     
    
      
  }
}
