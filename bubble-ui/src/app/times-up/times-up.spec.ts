import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TimesUp } from './times-up';

describe('TimesUp', () => {
  let component: TimesUp;
  let fixture: ComponentFixture<TimesUp>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TimesUp],
    }).compileComponents();

    fixture = TestBed.createComponent(TimesUp);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
