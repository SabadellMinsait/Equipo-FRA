package com.minsait.curso.service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.minsait.curso.client.AlumnoCliente;
import com.minsait.curso.client.InscripcionCliente;
import com.minsait.curso.client.StatusBibliotecaClient;
import com.minsait.curso.model.entity.Alumno;
import com.minsait.curso.model.entity.Inscripcion;
import com.minsait.curso.model.entity.Materia;
import com.minsait.curso.model.entity.Profesor;
import com.minsait.curso.model.entity.StatusBiblioteca;
import com.minsait.curso.model.entity.TiraMaterias;
import com.minsait.curso.repository.TiraMateriaRepository;

import lombok.extern.slf4j.Slf4j;


/**
 * Implementaci&#243;n de TiraMateriasService para interactuar con el repositorio de TiraMaterias
 * @author Alejandro Vences
 * @version 1.0
 */

@Service
@Slf4j
public class TiraMateriasServiceImpl implements TiraMateriasService{

	@Autowired
	TiraMateriaRepository  repository;
	
	@Autowired
	StatusBibliotecaClient statusBibliotecaClient;
	
	@Autowired
	AlumnoCliente alumnoCliente;
	
	@Autowired
	InscripcionCliente inscripcionCliente;
	
	@Autowired
	ProfesorService profesorService;
	
	@Autowired
	MateriaService materiaService;
	
	
	/**
	 * Funci&#243;n para recuperar la lista completa de tiras de materias registradas 
	 * @return Regresa un List de la clase tiraMateria
	 */
	@Override
	public List<TiraMaterias> findAll() {
		return  repository.findAll();
	}
	
	/**
	 * Funci&#243;n para recuperar el detalle de la tira de materia
	 * @param idInsMateria: identificador de la tira de materia
	 * @return Registro de una tira de materia
	 */
	@Override
	public Optional<TiraMaterias> findById(Long idInsMateria) {
		// Buscamos por identificador con el repositorio JPA
		return repository.findById(idInsMateria);
	}
	
	
	/**
	 * Funci&#243;n para recuperar la lista completa de alumnos registrados 
	 * @return Regresa un List de la clase alumno
	 */
	@Override
	public TiraMaterias validaMateria(TiraMaterias tiraMaterias) {
		log.info("Entrando a la validación del alumno");
		// Validamos que el alumno no sea vacio
		Assert.notNull(tiraMaterias.getIdInsMateria(), "El alumno no puede ser nulo");
		// Validamos si el alumno existe
		ResponseEntity<Alumno> alumno = null;
		try {
			alumno = alumnoCliente.findById(tiraMaterias.getNumCuenta());
		}catch (Exception e) {
			Assert.isTrue(alumno != null, "Alumno no existe");
		}
		log.info("Alumno recuperado: " + alumno);
		Assert.isTrue(alumno != null , "El alumno no existe");
		// Aseguramos que el alumno este correcto
		ResponseEntity<Inscripcion> inscripcion = null;
		try {
			inscripcion = inscripcionCliente.findByNumCuenta(tiraMaterias.getNumCuenta());
		}catch (Exception e) {
			Assert.isTrue(inscripcion != null, "Alumno no inscrito");
		}
		//Fecha del sistema
		Date fechaSys = new Date();
		log.info("fecha del myDate: {}", fechaSys);
		log.info("fecha final: {}", inscripcion.getBody().getFechaFinal());
		
		Date  fechaFin = new java.util.Date(inscripcion.getBody().getFechaFinal().getTime());
		log.info("fecha Fin: {}", fechaFin);
		
		Assert.isTrue(fechaFin.after(fechaSys), "Alumno fuera de periodo");
		
		tiraMaterias.setIdPeriodo(inscripcion.getBody().getIdPeriodo());
			
		if (tiraMaterias.getProfesor() !=null) {
			Optional<Profesor> profesor = profesorService.findById(tiraMaterias.getProfesor().getIdProfesor());
			Assert.isTrue(profesor.isPresent(),"El profesor no existe");			
		}
		
		if (tiraMaterias.getMateria() !=null) {
			Optional<Materia> materia = materiaService.findById(tiraMaterias.getMateria().getIdMateria());
			Assert.isTrue(materia.isPresent(),"La materia no existe");			
		}
		
		
		//TiraMaterias.setAlumno(alumno.get());
		// Validamos el status de la bilbioteca
		log.info("Recuperando el estatus de la biblioteca");
		StatusBiblioteca statusBiblioteca = statusBibliotecaClient.findByNumcuenta(tiraMaterias.getNumCuenta()).getBody();
		log.info("Estatus recuperado: " + statusBiblioteca);
		Assert.isTrue(!statusBiblioteca.getStatus().equals("Con adeudo"), "El alumno tiene adeudos en la biblioteca");
		// regresamos la inscripcion
		return tiraMaterias;
	}
	
	
	/**
	 * Funci&#243;n para guardar una nueva tira de materia
	 * @param tiraMaterias: Registro de la tira de materia
	 * @return Registro de la tira de materia guardada
	 */
	@Override
	public TiraMaterias create(TiraMaterias tiraMaterias) {
		// Valida que el parámetro no se nulo
		Assert.notNull(tiraMaterias, "La tira de materia no puede ser nulo");
		// Se valida si cuenta ya con numero de cuenta
		if (tiraMaterias.getIdInsMateria() != null) {
			Optional<TiraMaterias> periodoActual = findById(tiraMaterias.getIdInsMateria());
			Assert.isTrue(!periodoActual.isPresent(), "El id perdiodo ya existe");
		}
		// Validamos el alumno
		tiraMaterias = validaMateria(tiraMaterias);

		return repository.save(tiraMaterias);
	}
	
	
	/**
	 * Funci&#243;n para guardar una tira de materia 
	 * @param IdInsMateria: identificador de la tira de materia
	 * @param tiraMaterias: Registro de la tira de materia
	 * @return Registro de la tira de materia
	 */
	@Override
	public TiraMaterias save(Long IdInsMateria, TiraMaterias tiraMaterias) {
		// Aseguramos que el numero de cuenta sea el mismo
		tiraMaterias.setIdInsMateria(IdInsMateria);
		// Se guarda el registro por el repositorio de JPA 
		return repository.save(tiraMaterias);
	}

}
