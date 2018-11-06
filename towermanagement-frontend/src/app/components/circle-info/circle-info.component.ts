import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Tower } from '../../model/tower';
import { CSA } from '../../model/csa';
import { CirclesService } from '../../service/circles.service';
import { TowerService } from '../../service/tower.service';
import { CSADetailsService } from '../../service/csadetails.service';
import { TokenStorage } from '../../core/token.storage';

@Component({
  selector: 'app-circle-info',
  templateUrl: './circle-info.component.html',
  styleUrls: ['./circle-info.component.css']
})
export class CircleInfoComponent implements OnInit {
  towers: Tower[];
  circle:string;
  csaIdd:number;
  csa:CSA;
  parsed2;
  fetchToken:string;
 mapurl:string;
  constructor(private router: Router, private activatedRoute: ActivatedRoute, 
    private circleService: CirclesService,private towerService: TowerService,
    private csaServ:CSADetailsService,private token:TokenStorage
    ) { 
      
    }

  ngOnInit() {
    this.activatedRoute.params.subscribe(
      (params) => {
        this.circle = params['circle'];
        console.log(this.circle);
        if (this.circle) {
          this.circleService.getTowerByCircle(this.circle).subscribe(
            (data) =>{ this.towers = data;
              this.csaIdd=this.towers[0].csa.csaId;
              console.log(this.csaIdd);
 
              this.csaServ.getCsaByCsaId(this.csaIdd).subscribe(
                (data)=>{
                  this.csa=data
                  console.log(this.csa.csaId);
                },
                (err)=>{
                  alert("No CSA FOUND");
                });
            }
          );
        }
      },(err)=>{
        alert("No Towers FOUND");
      }
    );

    this.activatedRoute.params.subscribe(
      (params) => {
        let csaId = params['id'];
        this.towers=null;
        console.log(csaId);
        if (csaId) {
          this.towerService.getTowerByCsaId(csaId).subscribe(
            (data) => this.towers = data
          );
        }
      },
      (err)=>{
        alert("No Towers found")
      }
    );

    console.log("Current Circle----->"+this.circle);
    this.display(this.circle);

    console.log("Url for map"+this.mapurl);
    
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

  showCsa(){
   // console.log("CHECCKINGGG---->"+this.csaIdd);
    
  document.getElementById("overlayforCsa").style.display = "block";
  }

  off() {
    document.getElementById("overlayforCsa").style.display = "none";
  }

  showMapp(){
    document.getElementById("overlayforMap").style.display = "block";
  }
  offMap() {
    document.getElementById("overlayforMap").style.display = "none";
  }

  display(circleName:string)
  {
    console.log("Inside Display for circle");
    switch(circleName){
      case "Chennai":
      this.mapurl="https://www.google.com/maps/d/u/0/embed?mid=1YCaodNfz0qfs7VLoDatajEsIeAkRPKew";
      break;
      
      case "Mumbai":
      this.mapurl="https://www.google.com/maps/d/u/0/embed?mid=1UrbK2-vNZ1FnlyY43zUgUwXnOqbYMw08";
      break;
      
      case "Delhi":
      this.mapurl="https://www.google.com/maps/d/u/0/embed?mid=1zxyFJA61TTEHseuor14-GYmtmph3x5qV";
      break;
      
      case "Madurai":
      this.mapurl="https://drive.google.com/open?id=1Y_B5wlx8rM1MToVYjTsBQ5MFejh6yDAW&usp=sharing";
      break;
      
      case "Mysore":
      this.mapurl="https://drive.google.com/open?id=1JH7Ug99fDiEX7fZuFx5Dk03Bx1m7QZvR&usp=sharing";
      break;
      
      case "Agra":
      this.mapurl="https://drive.google.com/open?id=1oao9fkpJm2KiUxCNVggiiQUEx7drPmLT&usp=sharing";
      break;
      
      case "Bangalore":
      this.mapurl="https://www.google.com/maps/d/u/0/embed?mid=1ky8vVN7CvTrJkoF0NFOtU19vUszsx8x9" ;
      break;
      
      case "Hyderabad":
      this.mapurl="https://www.google.com/maps/d/u/0/embed?mid=1ensWDXQarq3dLpjZWmlbntOvJqjEOfCq";
      break;
      
      case "Jaipur":
      this.mapurl="https://www.google.com/maps/d/u/0/embed?mid=1vuP_26rQYCHkqg9gbe0LZlL7oI2sZ491";
      break;

      default:
      this.mapurl="https://www.google.co.in"
      break;
    }
  }

}
