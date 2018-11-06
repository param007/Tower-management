import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CSADetailsComponent } from './csadetails.component';

describe('CSADetailsComponent', () => {
  let component: CSADetailsComponent;
  let fixture: ComponentFixture<CSADetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CSADetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CSADetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
