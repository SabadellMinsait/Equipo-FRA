package com.minsait.curso.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.minsait.curso.client.StatusBibliotecaClient;
import com.minsait.curso.model.entity.Alumno;
import com.minsait.curso.model.entity.Inscripcion;
import com.minsait.curso.model.entity.StatusBiblioteca;
import com.minsait.curso.repository.InscripcionRepository;

import lombok.extern.slf4j.Slf4j;


/**
 * Implementaci&#243;n de InscripcionService para interactuar con el repositorio de Inscripci&#243;n
 * @author fvelez
 * @version 1.0
 */
@Service
@Slf4j
public class InscripcionServiceImpl implements InscripcionService{

	/**
	 * Propiedad repositorio de tipo InscrpcionRepository
	 */
	@Autowired
	InscripcionRepository repository;
	/**
	 * Propiedad repositorio de alumno
	 */
	@Autowired 
	AlumnoService alumnoService;
	/**
	 * Propiedad repositorio de estatus de biblioteca del repositorio bibliotecaClient
	 */
	@Autowired
	StatusBibliotecaClient statusBibliotecaClient; 
	
	/**
	 * Funci&#243;n para recuperar la lista completa de inscrpciones registrados 
	 * @return Regresa un List de la clase Inscrpcion
	 */
	@Override
	public List<Inscripcion> findAll(){
		// Regresamos la lista genetada por la implementación a JPA
		return repository.findAll();
	}
	
	/**
	 * Funci&#243;n para recuperar la inscrpci&#243;n por id periodo
	 * @param idPeriodo: Identificador del periodo
	 * @return Registro de la inscripcii&#243;n
	 */
	@Override
	public Optional<Inscripcion> findById(Long idPeriodo) {
		// Buscamos por identificador con el repositorio JPA
		return repository.findById(idPeriodo);
	}
	
	/**
	 * Funci&#243;n para recuperar la &#250;ltima inscrpciones de un alumno
	 * @param numCuenta: n&#250;mero del alumno 
	 * @return Registro de la &#250;ltima inscripci&#243;n
	 */
	@Override
	public Optional<Inscripcion> findByNumCuenta(Long numCuenta) {
		// Obtenemos la lista de inscripciones del alumno
		List<Inscripcion> inscripciones = repository.findByNumCuentaOrderByFechaIngresoDesc(numCuenta);
		// Valida que tenga por lo menos un resultado
		if (inscripciones.size() == 0)
			return Optional.empty();
		// Regresamos la última inscripcion 
		return Optional.of(inscripciones.get(0));
	}

	/** 
	 * Validaci&#243;n del registro de inscripci&#243;n
	 * @param inscripcion: Registro de la inscripci&#243;n a validar
	 * @return Registro de la inscripci&#243;n
	 */
	public Inscripcion validaAlumno(Inscripcion inscripcion) {
		log.info("Entrando a la validación del alumno");
		// Validamos que el alumno no sea vacio
		Assert.notNull(inscripcion.getAlumno(), "El alumno no puede ser nulo");
		// Validamos si el alumno existe
		Optional<Alumno> alumno = alumnoService.findById(inscripcion.getAlumno().getNumCuenta());
		log.info("Alumno recuperado: " + alumno);
		Assert.isTrue(alumno.isPresent(), "El alumno no existe");
		// Aseguramos que el alumno este correcto
		inscripcion.setAlumno(alumno.get());
		// Validamos el status de la bilbioteca
		log.info("Recuperando el estatus de la biblioteca");
		StatusBiblioteca statusBiblioteca = statusBibliotecaClient.findByNumcuenta(inscripcion.getAlumno().getNumCuenta()).getBody();
		log.info("Estatus recuperado: " + statusBiblioteca);
		Assert.isTrue(!statusBiblioteca.getStatus().equals("Con adeudo"), "El alumno tiene adeudos en la biblioteca");
		// regresamos la inscripcion
		return inscripcion;
	}
	
	/**
	 * Funci&#243;n para registrar la inscrpci&#243;n de un alumno
	 * @param inscripcion: Registro de la inscripci&#243;n a crear
	 * @return Registro de la inscripci&#243;n
	 */
	@Override
	public Inscripcion create(Inscripcion inscripcion) {
		// Valida que el parámetro no se nulo
		Assert.notNull(inscripcion, "El registro de inscripción no puede ser nulo");
		// Se valida si cuenta ya con numero de cuenta
		if (inscripcion.getIdPeriodo() != null) {
			Optional<Inscripcion> periodoActual = findById(inscripcion.getIdPeriodo());
			Assert.isTrue(!periodoActual.isPresent(), "El id perdiodo ya existe");
		}
		// Validamos el alumno
		inscripcion = validaAlumno(inscripcion);
		// Validamos las fechas
		Assert.notNull(inscripcion.getFechaInicio(), "Fecha inicio no puede ser nula");
		Assert.notNull(inscripcion.getFechaFinal(), "Fecha final no puede ser nula");
		// Validamos si el periodo ya existe
		List<Inscripcion> inscripciones = repository.findByNumCuentaOrderByFechaIngresoDesc(inscripcion.getAlumno().getNumCuenta());
		// validamos si el periodo no existe
		Date fechaInicial = inscripcion.getFechaInicio();
		Date fechaFinal = inscripcion.getFechaFinal();
		List<Inscripcion> perdiosIguales = inscripciones.stream().filter(c-> c.getFechaInicio().equals(fechaInicial) && c.getFechaFinal().equals(fechaFinal)).collect(Collectors.toList());
		// Se genera un error si ya existe el periodo
		Assert.isTrue(perdiosIguales.stream().count() == 0, "El periodo ya existe");
		// Se guarda el registro por el repositorio de JPA 
		return repository.save(inscripcion);
	}

	/**
	 * Funci&#243;n para actualizar la inscrpci&#243;n de un alumno
	 * @param idPerdiodo: Identificador del periodo
	 * @param inscripcion: Registro de la inscripci&#243;n a actualizar
	 * @return Registro de la inscripci&#243;n
	 */
	@Override
	public Inscripcion save(Long idPerdiodo, Inscripcion inscripcion) {
		// Validamos el alumno
		inscripcion = validaAlumno(inscripcion);
		// Aseguramos que el id periodo sea el mismo
		inscripcion.setIdPeriodo(idPerdiodo);
		return repository.save(inscripcion);
	}

	/**
	 * Creaci&#243;n de un InscripcionService vac&#237;o
	 */
	public InscripcionServiceImpl() {
		
	}
	
}
