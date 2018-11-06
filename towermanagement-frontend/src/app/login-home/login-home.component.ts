import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/core/auth.service';
import { TokenStorage } from 'src/app/core/token.storage';
import { NavBarService } from '../service/navbar.service';


@Component({
  selector: 'app-login-home',
  templateUrl: './login-home.component.html',
  styleUrls: ['./login-home.component.css']
})
export class LoginHomeComponent implements OnInit {

  constructor(private router: Router, private authService: AuthService,
     private token: TokenStorage,private nav:NavBarService,
     private activatedRoute:ActivatedRoute) {
  }
  currentToken:string;
  parsed;
  parsed2;
  fetchToken:string;
  username: string;
  password: string;
ngOnInit(){
  this.activatedRoute.params.subscribe(val => {
    // put the code from `ngOnInit` here
    
    this.nav.hide();
  });
    
   this.fetchToken=this.token.getToken();
   this.parsed2=this.parseJwt(this.fetchToken);


         if(this.parsed2.scopes=='ROLE_ADMIN'){
         this.router.navigateByUrl('/admin');
         console.log("ADDDDMIN")
         }
         else{
           this.router.navigateByUrl('/csa'); 
         console.log("USEEEER");
         }

}
  login(): void {
    this.authService.attemptAuth(this.username, this.password).subscribe(
      data => {
        
        this.token.saveToken(data.token);
        
        this.currentToken=this.token.getToken();
        
        console.log("SESSION TOKEN :---> "+this.currentToken);
        
        this.parsed=this.parseJwt(this.currentToken);

        console.log(this.parsed.scopes);
        console.log(this.parseJwt(this.currentToken));

        if(this.parsed.scopes=='ROLE_ADMIN'){
          window.location.replace('/admin');
       // this.router.navigateByUrl('/admin');
        console.log("ADDDDMIN")
        }
        else{
         // this.router.navigateByUrl('/csa'); 
         window.location.replace('/csa')
         console.log("USEEEER");
        }
        console.log("SESSION TOKEN :---> "+this.token.getToken()); 
      },
      (error)=>{
        alert("Invalid Username or Password");
      }
    );
  }
  parseJwt (token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    return JSON.parse(window.atob(base64));
};
}
