import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { YearMoviesComponent } from './year-movies.component';

describe('YearMoviesComponent', () => {
  let component: YearMoviesComponent;
  let fixture: ComponentFixture<YearMoviesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ YearMoviesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(YearMoviesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
