import { Component, OnInit } from '@angular/core';
import { TowerService } from 'src/app/service/tower.service';
import { Tower } from 'src/app/model/tower';

import { CsaService } from 'src/app/service/csa.service';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { ActivatedRoute, Router } from '@angular/router';
import { CSA } from '../../model/csa';
import { TokenStorage } from '../../core/token.storage';
import { NavBarService } from '../../service/navbar.service';
import { User } from 'src/app/model/user';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
 
imgSrc:string[];
actualSrc:string;
towers:Tower[];
csa:CSA;
tower:Tower;
closeResult: string;
mapSrc:string;
fetchToken:string;
currentCSA:User;
parsed2;
  constructor(private tSer:TowerService,
    private cSer:CsaService,
    private modalService: NgbModal,
    private router:Router,private token:TokenStorage,public nav: NavBarService,private csaServ:CsaService) {     
      this.imgSrc=["","","https://www.google.com/maps/d/u/0/embed?mid=1YCaodNfz0qfs7VLoDatajEsIeAkRPKew","",
"https://www.google.com/maps/d/u/0/embed?mid=1ensWDXQarq3dLpjZWmlbntOvJqjEOfCq"  ,"",
"https://www.google.com/maps/d/u/0/embed?mid=1UrbK2-vNZ1FnlyY43zUgUwXnOqbYMw08" ,
"https://drive.google.com/open?id=1JH7Ug99fDiEX7fZuFx5Dk03Bx1m7QZvR&usp=sharing",
"https://drive.google.com/open?id=1Y_B5wlx8rM1MToVYjTsBQ5MFejh6yDAW&usp=sharing",
"https://www.google.com/maps/d/u/0/embed?mid=1ky8vVN7CvTrJkoF0NFOtU19vUszsx8x9 ",
" https://drive.google.com/open?id=1oao9fkpJm2KiUxCNVggiiQUEx7drPmLT&usp=sharing",
" https://www.google.com/maps/d/u/0/embed?mid=1zxyFJA61TTEHseuor14-GYmtmph3x5qV",
"https://www.google.com/maps/d/u/0/embed?mid=1vuP_26rQYCHkqg9gbe0LZlL7oI2sZ491",
" https://drive.google.com/open?id=1d9YciZQZEorp3LoIBItMzw0YWMubOoUJ&usp=sharing"];

  }

  ngOnInit() {
    this.nav.show();
    
   
    
     
          
        
   
    
    // this.cSer.getCsa().subscribe(
    //   (data)=>{this.csa=data
    // }
    // );
    this.fetchToken=this.token.getToken();
    if(this.fetchToken){
    this.parsed2=this.parseJwt(this.fetchToken);
    this.csaServ.getCsaByUsername(this.parsed2.sub).subscribe(
      (data)=>{
        this.currentCSA=data;
        this.tSer.getTowerByCsaId(this.currentCSA.id).subscribe(
          (data)=>{ this.towers=data;
            this.actualSrc=this.imgSrc[this.currentCSA.id];
        }
        );
      },(error)=>{
        alert("CSA fetching failed");
      });
 
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
 
  raiseFn(){
    this.csa.csaId=this.towers[1].csa.csaId;
    this.router.navigateByUrl("/form/"+this.csa.csaId);
  }
  viewTower(towerId){
    this.tSer.getTowerById(towerId).subscribe(
      (data)=>{this.tower=data
      console.log(data);}
    )

  }
  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }
  openVerticallyCentered(content) {
    this.modalService.open(content, { centered: true });
  }
}


