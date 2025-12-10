export class Cliente {
  constructor(
    public idCliente: number,
    public nombre: string,
    public apellido: string,
    public dni: string,
    public telefono: string,
    public direccion: string,
    public email: string,
    public licenciaConducir: string,
    public usuario: any,
    public reservas: any[],
    public pagos: any[]
  ) {}
}