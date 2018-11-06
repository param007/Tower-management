import { TestBed } from '@angular/core/testing';

import { CirclesService } from './circles.service';

describe('CirclesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CirclesService = TestBed.get(CirclesService);
    expect(service).toBeTruthy();
  });
});
