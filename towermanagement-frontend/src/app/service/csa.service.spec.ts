import { TestBed } from '@angular/core/testing';

import { CsaService } from './csa.service';

describe('CsaService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CsaService = TestBed.get(CsaService);
    expect(service).toBeTruthy();
  });
});
