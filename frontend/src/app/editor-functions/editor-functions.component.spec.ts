import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditorFunctionsComponent } from './editor-functions.component';

describe('EditorFunctionsComponent', () => {
  let component: EditorFunctionsComponent;
  let fixture: ComponentFixture<EditorFunctionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditorFunctionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditorFunctionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
