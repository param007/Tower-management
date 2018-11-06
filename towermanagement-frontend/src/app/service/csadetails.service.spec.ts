import { TestBed } from '@angular/core/testing';

import { CSADetailsService } from './csadetails.service';

describe('CSADetailsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CSADetailsService = TestBed.get(CSADetailsService);
    expect(service).toBeTruthy();
  });
});
