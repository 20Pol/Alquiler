import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardProgramComponent } from './dashboard-program.component';

describe('DashboardProgramComponent', () => {
  let component: DashboardProgramComponent;
  let fixture: ComponentFixture<DashboardProgramComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DashboardProgramComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DashboardProgramComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
