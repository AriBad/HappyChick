import { TestBed } from '@angular/core/testing';

import { RecapHttpService } from './recap-http.service';

describe('RecapHttpService', () => {
  let service: RecapHttpService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RecapHttpService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
