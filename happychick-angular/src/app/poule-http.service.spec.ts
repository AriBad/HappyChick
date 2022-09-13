import { TestBed } from '@angular/core/testing';

import { PouleHttpService } from './poule-http.service';

describe('PouleHttpService', () => {
  let service: PouleHttpService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PouleHttpService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
