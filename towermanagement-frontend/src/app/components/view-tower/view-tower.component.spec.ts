import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewTowerComponent } from './view-tower.component';

describe('ViewTowerComponent', () => {
  let component: ViewTowerComponent;
  let fixture: ComponentFixture<ViewTowerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewTowerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewTowerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
