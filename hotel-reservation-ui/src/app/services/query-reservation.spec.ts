import { TestBed } from '@angular/core/testing';

import { QueryReservation } from './query-reservation';

describe('QueryReservation', () => {
  let service: QueryReservation;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(QueryReservation);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
