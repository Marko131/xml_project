import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RawInputDialogComponent } from './raw-input-dialog.component';

describe('RawInputDialogComponent', () => {
  let component: RawInputDialogComponent;
  let fixture: ComponentFixture<RawInputDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RawInputDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RawInputDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
