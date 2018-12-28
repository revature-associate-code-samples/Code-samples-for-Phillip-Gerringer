import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ComedicMoviesComponent } from './comedic-movies.component';

describe('ComedicMoviesComponent', () => {
  let component: ComedicMoviesComponent;
  let fixture: ComponentFixture<ComedicMoviesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ComedicMoviesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ComedicMoviesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
