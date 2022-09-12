import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PoulaillerComponent } from './poulailler.component';

describe('PoulaillerComponent', () => {
  let component: PoulaillerComponent;
  let fixture: ComponentFixture<PoulaillerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PoulaillerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PoulaillerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
