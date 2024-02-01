package com.integracao.main.modelo.carro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.integracao.main.modelo.carro.entity.CarroEntity;

public interface CarroRepository extends JpaRepository<CarroEntity,Long>{
	
	
	
}
