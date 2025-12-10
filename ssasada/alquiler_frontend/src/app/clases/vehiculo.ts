export class Vehiculo {

  constructor(
    public idVehiculo: number,
    public placa: string,
    public marca: string,
    public modelo: string,
    public anio: number,
    public color: string,
    public estado: string,
    public asientos: number | null,
    public precioDiario: number,
    public combustible: string | null,
    public transmision: string | null,
    public tipoVehiculo: string,
    public kilometrajeActual: number,
    public imagenUrl: string,
    public descripcion: string
  ) {}

}
