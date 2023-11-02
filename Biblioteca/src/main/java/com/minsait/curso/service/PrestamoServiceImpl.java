package com.minsait.curso.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.minsait.curso.cliente.AlumnoCliente;
import com.minsait.curso.cliente.InscripcionCliente;
import com.minsait.curso.model.entity.Alumno;
import com.minsait.curso.model.entity.Inscripcion;
import com.minsait.curso.model.entity.Libro;
import com.minsait.curso.model.entity.Prestamo;
import com.minsait.curso.repository.PrestamoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PrestamoServiceImpl implements PrestamoService{

	@Autowired
	PrestamoRepository repository;
	
	@Autowired
	AlumnoCliente alumnoCliente;
	
	@Autowired
	InscripcionCliente inscripcionCliente;
	
	@Autowired
	LibroService libroServices;
	
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
		log.info("Alumno entrontrado: {}, status: {}", alumno.getBody(), alumno.getStatusCode());
		
		ResponseEntity<Inscripcion> inscripcion = null;
		try {
			inscripcion = inscripcionCliente.findByNumCuenta(prestamo.getNumCuenta());
		}catch (Exception e) {
			Assert.isTrue(inscripcion != null, "Alumno no inscrito");
		}
		
		log.info("Inscripción entrontrada: {}, status: {}", inscripcion.getBody(), inscripcion.getStatusCode());
		
		Optional<Libro> libro = libroServices.findById(prestamo.getLibro().getIdLibro());
		
		Assert.isTrue(libro.isPresent(), "El libro no existe");
		Assert.isTrue(alumno.getStatusCode()== HttpStatus.OK, "El alumno no existe");
		
		//Fecha del sistema
		Date fechaSys = new Date();
		log.info("fecha del myDate: {}", fechaSys);
		log.info("fecha final: {}", inscripcion.getBody().getFechaFinal());
		
		String fechaFormato = new SimpleDateFormat("yyyy-MM-dd").format(fechaSys);
		log.info("fecha del sistema: {}", fechaFormato);
		
		Date  fechaFin = new java.util.Date(inscripcion.getBody().getFechaFinal().getTime());
		log.info("fecha Fin: {}", fechaFin);
		
		Assert.isTrue(fechaFin.after(fechaSys), "Alumno fuera de periodo");
		
		prestamo.setIdPeriodo(inscripcion.getBody().getIdPeriodo());
		prestamo.setFechaSalida(fechaSys);
		prestamo.setFechaEntrega(null);
		
		Assert.isTrue(libro.get().getDisponibles().compareTo(0) > 0, "No hay libros disponibles");
		
		// Se valida si cuenta ya con numero de cuenta
		if (prestamo.getIdPrestamo() != null) {
			Optional<Prestamo> prestamoActual = findById(prestamo.getIdPrestamo());
			Assert.isTrue(!prestamoActual.isPresent(), "El Prestamo ya existe");
		}
		
		Libro restarLibro = libro.get();		
		restarLibro.setDisponibles(restarLibro.getDisponibles().intValue() - 1);		
		libroServices.save(restarLibro.getIdLibro(), restarLibro);
		
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

	@Override
	public List<Prestamo> findByNumCuenta(Long numCuenta) {
		log.info("NumCuenta {}", numCuenta);
		return repository.findByNumCuentaOrderByFechaSalidaAsc(numCuenta);
	}

	@Override
	public Optional<Prestamo> returnBook(Long numCuenta, Long idLibro) {
		List<Prestamo> prestamos = findByNumCuenta(numCuenta);
		Assert.isTrue(prestamos != null && prestamos.size() > 0, "El alumno no tiene prestamos pendientes");
		
		List<Prestamo> resultado = prestamos.stream().filter(c->c.getLibro().getIdLibro().equals(idLibro)).collect(Collectors.toList());
		Assert.isTrue(resultado != null && resultado.size() > 0, "No se encontro el prestamo con el libro");
		
		resultado.get(0).setFechaEntrega(new Date());
		
		save(resultado.get(0).getIdPrestamo(), resultado.get(0));
		
		Libro libroSuma = resultado.get(0).getLibro();
		libroSuma.setDisponibles(libroSuma.getDisponibles().intValue() + 1);
		
		libroServices.save(idLibro, libroSuma);
		
		return Optional.of(resultado.get(0));  
		
	}

}
