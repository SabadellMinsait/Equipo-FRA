package com.minsait.curso.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.minsait.curso.model.entity.Status;

@Service
public class StatusServiceImpl implements StatusService {

	@Override
	public Optional<Status> findByNumCuenta(Long numCuenta) {
		Status status = new Status();
		status.setNumCuenta(numCuenta);
		status.setStatus("Sin adeudo");
		Optional<Status> optional = Optional.of(status);
		
		return optional;
	}

}
