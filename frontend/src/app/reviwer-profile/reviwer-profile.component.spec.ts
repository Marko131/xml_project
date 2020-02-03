import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviwerProfileComponent } from './reviwer-profile.component';

describe('ReviwerProfileComponent', () => {
  let component: ReviwerProfileComponent;
  let fixture: ComponentFixture<ReviwerProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReviwerProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviwerProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
