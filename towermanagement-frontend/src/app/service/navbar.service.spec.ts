import { TestBed } from '@angular/core/testing';
import { NavBarService } from './navbar.service';



describe('NavBarService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: NavBarService = TestBed.get(NavBarService);
    expect(service).toBeTruthy();
  });
});
