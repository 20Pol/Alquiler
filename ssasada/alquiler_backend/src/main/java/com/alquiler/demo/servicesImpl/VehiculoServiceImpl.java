package com.alquiler.demo.servicesImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alquiler.demo.entities.Vehiculo;
import com.alquiler.demo.repositories.VehiculoRepository;
import com.alquiler.demo.services.VehiculoService;

@Service
public class VehiculoServiceImpl implements VehiculoService {

	private final VehiculoRepository vehiculoRepository;

	public VehiculoServiceImpl(VehiculoRepository vehiculoRepository) {
		this.vehiculoRepository = vehiculoRepository;
	}

	@Override
	public List<Vehiculo> findAll() {
		return vehiculoRepository.findAll();
	}

	@Override
	public List<Vehiculo> buscarPorMarca(String marca) {

		return vehiculoRepository.findByMarcaIgnoreCase(marca);
	}

	@Override
	public List<Vehiculo> buscarPorAnio(Integer anio) {

		return vehiculoRepository.findByAnio(anio);
	}

	@Override
	public List<Vehiculo> buscarPorTipo(String tipoVehiculo) {

		return vehiculoRepository.findByTipoVehiculo(tipoVehiculo);
	}

	@Override
	public Vehiculo obtenerPorId(Integer id) {
		return vehiculoRepository.findById(id).orElse(null);
	}

	@Override
	public Vehiculo save(Vehiculo vehiculo) {
		return vehiculoRepository.save(vehiculo);
	}

	@Override
	public Vehiculo update(Integer id, Vehiculo vehiculoDetails) {
		Vehiculo vehiculo = vehiculoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Vehículo no encontrado con id: " + id));

		vehiculo.setMarca(vehiculoDetails.getMarca());
		vehiculo.setModelo(vehiculoDetails.getModelo());
		vehiculo.setAnio(vehiculoDetails.getAnio());
		vehiculo.setColor(vehiculoDetails.getColor());
		vehiculo.setPrecioDiario(vehiculoDetails.getPrecioDiario());
		vehiculo.setKilometrajeActual(vehiculoDetails.getKilometrajeActual());
		vehiculo.setTipoVehiculo(vehiculoDetails.getTipoVehiculo());
		vehiculo.setDescripcion(vehiculoDetails.getDescripcion());
		vehiculo.setEstado(vehiculoDetails.getEstado());
		vehiculo.setImagenUrl(vehiculoDetails.getImagenUrl());
		vehiculo.setPlaca(vehiculoDetails.getPlaca());

		return vehiculoRepository.save(vehiculo);
	}

	@Override
	public void delete(Integer id) {
		vehiculoRepository.deleteById(id);
	}
}
