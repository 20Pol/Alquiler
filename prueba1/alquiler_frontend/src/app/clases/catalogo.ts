export class Catalogo {

  constructor(
    public idVehiculo: number,
    public placa: string,
    public marca: string,
    public modelo: string,
    public anio: number,
    public color: string,
    public estado: string,
    public asientos: number,
    public precioDiario: number,
    public tipoVehiculo: string,
    public kilometrajeActual: number,
    public sucursal: any,
    public imagen: string,
    public imagenUrl: string,
    public combustible: string,
    public transmision: string
  ) { }

}
