import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VehiculoListarComponent } from './vehiculo-listar.component';

describe('VehiculoListarComponent', () => {
  let component: VehiculoListarComponent;
  let fixture: ComponentFixture<VehiculoListarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VehiculoListarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VehiculoListarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
