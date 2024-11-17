import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PaystatusComponent } from './paystatus.component';

describe('PaystatusComponent', () => {
  let component: PaystatusComponent;
  let fixture: ComponentFixture<PaystatusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PaystatusComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PaystatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
