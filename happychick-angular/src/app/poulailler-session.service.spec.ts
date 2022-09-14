import { TestBed } from '@angular/core/testing';

import { PoulaillerSessionService } from './poulailler-session.service';

describe('PoulaillerSessionService', () => {
  let service: PoulaillerSessionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PoulaillerSessionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
