import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComicDetails } from './comic-details';

describe('ComicDetails', () => {
  let component: ComicDetails;
  let fixture: ComponentFixture<ComicDetails>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ComicDetails],
    }).compileComponents();

    fixture = TestBed.createComponent(ComicDetails);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
