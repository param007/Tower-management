import { TestBed } from '@angular/core/testing';

import { TowerService } from './tower.service';

describe('TowerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TowerService = TestBed.get(TowerService);
    expect(service).toBeTruthy();
  });
});
