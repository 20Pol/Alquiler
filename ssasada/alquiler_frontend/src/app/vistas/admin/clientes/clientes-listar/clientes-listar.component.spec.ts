import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientesListarComponent } from './clientes-listar.component';

describe('ClientesListarComponent', () => {
  let component: ClientesListarComponent;
  let fixture: ComponentFixture<ClientesListarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ClientesListarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClientesListarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
