package com.minsait.curso.service;

import java.util.Optional;

import com.minsait.curso.model.entity.Status;

public interface StatusService {

	public Optional<Status> findByNumCuenta(Long numCuenta);
	
}
