import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InternalError } from './internal-error';

describe('InternalError', () => {
  let component: InternalError;
  let fixture: ComponentFixture<InternalError>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InternalError],
    }).compileComponents();

    fixture = TestBed.createComponent(InternalError);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
