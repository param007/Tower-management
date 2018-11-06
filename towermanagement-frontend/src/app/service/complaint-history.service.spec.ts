import { TestBed } from '@angular/core/testing';

import { ComplaintHistoryService } from './complaint-history.service';

describe('ComplaintHistoryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ComplaintHistoryService = TestBed.get(ComplaintHistoryService);
    expect(service).toBeTruthy();
  });
});
