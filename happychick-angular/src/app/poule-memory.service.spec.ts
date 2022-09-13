import { TestBed } from '@angular/core/testing';

import { PouleMemoryService } from './poule-memory.service';

describe('PouleMemoryService', () => {
  let service: PouleMemoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PouleMemoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
