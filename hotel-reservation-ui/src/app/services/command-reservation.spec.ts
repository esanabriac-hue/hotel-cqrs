import { TestBed } from '@angular/core/testing';
import { provideHttpClient } from '@angular/common/http';
import { CommandService } from './command-reservation';

describe('CommandService', () => {
  let service: CommandService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient()]
    });
    service = TestBed.inject(CommandService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
