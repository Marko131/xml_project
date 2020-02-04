import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewerFunctionsComponent } from './reviewer-functions.component';

describe('ReviewerFunctionsComponent', () => {
  let component: ReviewerFunctionsComponent;
  let fixture: ComponentFixture<ReviewerFunctionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReviewerFunctionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewerFunctionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
