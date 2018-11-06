import { Component, Input } from '@angular/core';
import { ComplaintHistoryService } from './service/complaint-history.service';
import { Complaint } from './model/complaint';
import { Tower } from './model/tower';
import { TowerService } from './service/tower.service';
import { Router } from '@angular/router';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { TokenStorage } from './core/token.storage';
import { NavBarService } from './service/navbar.service';
import { CSA } from './model/csa';
import { User } from './model/user';
import { CsaService } from './service/csa.service';
import { CSADetailsService } from './service/csadetails.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Tower Management';
 
  towers:Tower[];
  closeResult: string;
  parsed2;
  currentUser:User;
  csa:CSA;
  fetchToken:string;
  @Input() isLoggedIn;
  constructor(private tSer:TowerService,
    private router:Router,
    private modalService: NgbModal,
    private token: TokenStorage,public nav: NavBarService,private csaServ:CsaService,
    private csaDetailServ:CSADetailsService){

    }
    ngOnInit() {
      this.isLoggedIn=this.token.isLoggedIn;

       this.fetchToken=this.token.getToken();

       this.parsed2=this.parseJwt(this.fetchToken);

       this.csaServ.getCsaByUsername(this.parsed2.sub).subscribe(
        (data)=>{
          this.currentUser=data;
            this.csaDetailServ.getCsaByCsaId(this.currentUser.id).subscribe(
                (data)=>{this.csa=data;}
            );});
           
           
          if(this.parsed2.scopes=='ROLE_ADMIN'){
             this.router.navigateByUrl('/admin');
            console.log("ADDDDMIN")
             }
             else{
               this.router.navigateByUrl('/csa'); 
             console.log("USEEEER");
          }

        }


      // this.tSer.getTowers().subscribe(
      //   (data)=>{ this.towers=data;
      //     console.log(this.towers);
      // }
      // );
    

  // raiseFn(){
  //   console.log("CALLED");
  //   this.router.navigateByUrl("/form/"+this.towers[1].csa.csaId);
  // }
  // open(content) {
  //   this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
  //     this.closeResult = `Closed with: ${result}`;
  //   }, (reason) => {
  //     this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
  //   });
  // }
  
  // private getDismissReason(reason: any): string {
  //   if (reason === ModalDismissReasons.ESC) {
  //     return 'by pressing ESC';
  //   } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
  //     return 'by clicking on a backdrop';
  //   } else {
  //     return  `with: ${reason}`;
  //   }
  // }

  parseJwt (token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    return JSON.parse(window.atob(base64));
};
signOff(){
  this.token.signOut();
  console.log("Logged out");
  //this.router.navigateByUrl("/");
  window.location.replace('/')
}
showCsa(){
  // console.log("CHECCKINGGG---->"+this.csaIdd);
   
 document.getElementById("overlayforCsa").style.display = "block";
 }

 off() {
   document.getElementById("overlayforCsa").style.display = "none";
 }
}
