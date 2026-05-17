import { TestBed } from '@angular/core/testing';

import { CommandReservation } from './command-reservation';

describe('CommandReservation', () => {
  let service: CommandReservation;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CommandReservation);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
