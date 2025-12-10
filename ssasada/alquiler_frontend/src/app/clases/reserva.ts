
import { Cliente } from "./cliente";
import { Vehiculo } from "./vehiculo";

export class Reserva {

  constructor(
    public idReserva: number,
    public fechaReserva: string,
    public fechaInicio: string,
    public fechaFin: string,
    public costoEstimado: number,
    public estado: string,
    public metodoEntrega: string,
    public direccionEntrega: string,
    public cliente?: Cliente | null,
    public vehiculo?: Vehiculo | null
  ) {}
}