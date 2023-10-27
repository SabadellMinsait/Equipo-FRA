package com.minsait.curso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.minsait.curso.cliente.AlumnoCliente;
import com.minsait.curso.model.entity.Alumno;
import com.minsait.curso.model.entity.Prestamo;
import com.minsait.curso.repository.PrestamoRepository;

@Service
public class PrestamoServiceImpl implements PrestamoService{

	@Autowired
	PrestamoRepository repository;
	
	@Autowired
	AlumnoCliente alumnoCliente;
	
	@Override
	public List<Prestamo> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Prestamo> findById(Long idPrestamo) {
		return repository.findById(idPrestamo);
	}

	@Override
	public Prestamo create(Prestamo prestamo) {
		// Valida que el parámetro no se nulo
		Assert.notNull(prestamo, "El prestamo no puede ser nulo");
		
		ResponseEntity<Alumno> alumno = alumnoCliente.findById(prestamo.getNumCuenta());
		
		Assert.isTrue(alumno.getStatusCode()!= HttpStatus.OK, "El alumno no existe"); 
		
		// Se valida si cuenta ya con numero de cuenta
		if (prestamo.getIdPrestamo() != null) {
			Optional<Prestamo> prestamoActual = findById(prestamo.getIdPrestamo());
			Assert.isTrue(!prestamoActual.isPresent(), "El Prestamo ya existe");
		}
		// Se guarda el registro por el repositorio de JPA
		return repository.save(prestamo);
	}

	@Override
	public Prestamo save(Long idPrestamo, Prestamo prestamo) {
		// Aseguramos que el numero de cuenta sea el mismo
		prestamo.setIdPrestamo(idPrestamo);
		// Se guarda el registro por el repositorio de JPA 
		return repository.save(prestamo);
	}

	@Override
	public void delete(Long idPrestamo) {
		// Se guarda el registro por el repositorio de JPA 
		repository.deleteById(idPrestamo);
		
	}
	
	/**
	 * Creacion de un LibroService vac&#237;o
	 */
	public PrestamoServiceImpl() {
		
	}

}
