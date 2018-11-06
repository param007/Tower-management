import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import {FormsModule} from '@angular/forms';
import { CSADetailsComponent } from './components/csadetails/csadetails.component';
import { ComplaintHistoryComponent } from './components/complaint-history/complaint-history.component';
import { HttpModule } from '@angular/http';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { NgbModule, NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { TowerDetailsComponent } from './components/tower-details/tower-details.component';
import { ComplaintViewComponent } from './components/complaint-view/complaint-view.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { ModalModule } from 'angular-custom-modal';
import { CircleComponent } from './components/circle/circle.component';
import { CircleInfoComponent } from './components/circle-info/circle-info.component';
import { Page2Component } from './components/page2/page2.component';
import { ViewTowerComponent } from './components/view-tower/view-tower.component';
import { SafeUrlPipe } from './Pipe/safe-url.pipe';

import { HomeComponent } from './ForCSA/home/home.component';
import { TicketComponent } from './ForCSA/ticket/ticket.component';
import { StatusComponent } from './ForCSA/status/status.component';
import { TowerComponent } from './ForCSA/tower/tower.component';
import { LoginHomeComponent } from './login-home/login-home.component';

import { TowerComplaintComponent } from './ForCSA/tower-complaint/tower-complaint.component';

import {UserService} from "./app.service";
import {AuthService} from "./core/auth.service";
import {Interceptor} from "./core/inteceptor";

import {TokenStorage} from "./core/token.storage";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { ForgotPassComponent } from './forgot-pass/forgot-pass.component';
import { SetNewPasswordComponent } from './set-new-password/set-new-password.component';
import { SignedOutComponent } from './signed-out/signed-out.component';
import { FilterStatusPipe } from './pipes/filter-status.pipe';
import {NgxPaginationModule} from 'ngx-pagination';


const routeMap: Routes = [
  { path: '', component:LoginHomeComponent  },
 
  { path: "admin", component: CircleComponent },
  { path: "csadetails", component: CSADetailsComponent},
  { path: "complainthistory", component: ComplaintHistoryComponent },
  { path: "complaints/:towerId", component: ComplaintHistoryComponent },
  { path:"complaints/csa/:id",component:ComplaintHistoryComponent },
  { path:"complaintview/:id",component: ComplaintViewComponent},
  { path:"towers/:id",component:TowerDetailsComponent},
  
  { path: 'circle', component: CircleComponent },
  { path: 'circle-info/:circle', component: CircleInfoComponent },
  { path: 'tower-info/:id', component: CircleInfoComponent },
  { path: 'page2', component: Page2Component },
  { path: 'viewTower/:id', component:ViewTowerComponent },
 
  { path: 'csa', component: HomeComponent },
  { path: 'form/:csaId', component: TicketComponent },
  { path: 'form/:csaId/:towerId', component: TicketComponent },
  { path: 'status', component: StatusComponent },
  { path: 'towerr/:towerId', component: TowerComponent },
  {path:'towCom/:towerId',component:TowerComplaintComponent},
  { path: 'forgot', component:ForgotPassComponent},
  {path:'setNewPassword',component:SetNewPasswordComponent},
{path:'signOut',component:SignedOutComponent}

];
@NgModule({
  declarations: [
    AppComponent,
    CSADetailsComponent,
    ComplaintHistoryComponent,
    TowerDetailsComponent,
    ComplaintViewComponent,
    NavBarComponent,
    CircleComponent,
    CircleInfoComponent,
    Page2Component,
    ViewTowerComponent,
    SafeUrlPipe,
    HomeComponent,
    TicketComponent,
    StatusComponent,
    TowerComponent,
    TowerComplaintComponent,
    LoginHomeComponent,
    ForgotPassComponent,
    SetNewPasswordComponent,
    SignedOutComponent,
    FilterStatusPipe
  
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routeMap),
    NgbModule.forRoot(),
    HttpModule,
    HttpClientModule,
    AngularFontAwesomeModule,
    ModalModule,
    FormsModule,
    NgbModule.forRoot(),
    NgxPaginationModule

  ],
  providers: [UserService, AuthService, TokenStorage, TokenStorage,
    {provide: HTTP_INTERCEPTORS,
    useClass: Interceptor,
    multi : true}
  ],
  bootstrap: [AppComponent],
})
export class AppModule { }
