import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TowerComplaintComponent } from './tower-complaint.component';

describe('TowerComplaintComponent', () => {
  let component: TowerComplaintComponent;
  let fixture: ComponentFixture<TowerComplaintComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TowerComplaintComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TowerComplaintComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
