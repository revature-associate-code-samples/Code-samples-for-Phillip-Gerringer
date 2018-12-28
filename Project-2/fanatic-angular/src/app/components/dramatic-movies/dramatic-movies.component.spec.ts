import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DramaticMoviesComponent } from './dramatic-movies.component';

describe('DramaticMoviesComponent', () => {
  let component: DramaticMoviesComponent;
  let fixture: ComponentFixture<DramaticMoviesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DramaticMoviesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DramaticMoviesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
