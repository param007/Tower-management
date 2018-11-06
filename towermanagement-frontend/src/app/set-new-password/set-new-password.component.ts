import { Component, OnInit } from '@angular/core';
import { AdminService } from '../service/admin.service';
import { Router } from '@angular/router';
import { Admin } from '../model/admin';
import { User } from '../model/user';

@Component({
  selector: 'app-set-new-password',
  templateUrl: './set-new-password.component.html',
  styleUrls: ['./set-new-password.component.css']
})
export class SetNewPasswordComponent implements OnInit {
password:string;
Otp:string;
status:boolean;
username:string;
confPass:string;
isCorrect:boolean;

constructor(private adminService:AdminService,private router:Router) { }
user:User;
  ngOnInit() {
  }

  checkSame(confPass: string) {
    this.confPass = this.confPass;
    if (this.confPass === this.password) {
      this.isCorrect=true;
    } else {
      this.isCorrect=false;
    }
  }
  
  resetPassword(){
    console.log("working reset inside");
    if(this.adminService.checkOtp(this.Otp)){
      console.log("Otp matches");
      this.adminService.changePassword(this.username,this.password).subscribe(
      (data)=>
        this.user=data,
        (err)=>console.log("Password not changed")
      );
      //console.log("Admin Username------>"+this.admin.username);
      console.log("Password changed");
      this.router.navigateByUrl('/');
    }
  }
}
