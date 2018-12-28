import { TestBed } from '@angular/core/testing';

import { ReviewApiService } from './review-api.service';

describe('ReviewApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ReviewApiService = TestBed.get(ReviewApiService);
    expect(service).toBeTruthy();
  });
});
